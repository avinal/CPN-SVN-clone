(************************************************************************)
(* CPN Tools Simulator (Simulator/CPN)                                  *)
(* Copyright 2010-2011 AIS Group, Eindhoven University of Technology    *)
(* All rights reserved.                                                 *)
(*                                                                      *)
(* This file is part of the CPN Tools Simulator (Simulator/CPN).        *)
(*                                                                      *)
(* You can choose among two licenses for this code, either the GNU      *)
(* General Public License version 2 or the below 4-clause BSD License.  *)
(*                                                                      *)
(************************************************************************)
(* GNU General Public License for CPN Tools Simulator (Simulator/CPN)   *)
(*                                                                      *)
(* CPN Tools is free software: you can redistribute it and/or modify    *)
(* it under the terms of the GNU General Public License as published by *)
(* the Free Software Foundation, either version 2 of the License, or    *)
(* (at your option) any later version.                                  *)
(*                                                                      *)
(* CPN Tools is distributed in the hope that it will be useful,         *)
(* but WITHOUT ANY WARRANTY; without even the implied warranty of       *)
(* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the        *)
(* GNU General Public License for more details.                         *)
(*                                                                      *)
(* You should have received a copy of the GNU General Public License    *)
(* along with CPN Tools.  If not, see <http://www.gnu.org/licenses/>.   *)
(************************************************************************)
(* 4-clause BSD License for CPN Tools Simulator (Simulator/CPN)         *)
(*                                                                      *)
(* Redistribution and use in source and binary forms, with or without   *)
(* modification, are permitted provided that the following conditions   *)
(* are met:                                                             *)
(*                                                                      *)
(* 1. Redistributions of source code must retain the above copyright    *)
(* notice, this list of conditions and the following disclaimer.        *)
(* 2. Redistributions in binary form must reproduce the above copyright *)
(* notice, this list of conditions and the following disclaimer in the  *)
(* documentation and/or other materials provided with the distribution. *)
(* 3. All advertising materials mentioning features or use of this      *)
(* software must display the following acknowledgement: “This product   *)
(* includes software developed by the AIS Group, Eindhoven University   *)
(* of Technology and the CPN Group, Aarhus University.”                 *)
(* 4. Neither the name of the AIS Group, Eindhoven University of        *)
(* Technology, the name of Eindhoven University of Technology, nor the  *)
(* names of its contributors may be used to endorse or promote products *)
(* derived from this software without specific prior written permission.*)
(*                                                                      *)
(* THIS SOFTWARE IS PROVIDED BY THE AIS GROUP, EINDHOVEN UNIVERSITY OF  *)
(* TECHNOLOGY AS IS AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING,   *)
(* BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND    *)
(* FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL   *)
(* THE AIS GROUP, EINDHOVEN UNIVERSITY OF TECHNOLOGY BE LIABLE FOR ANY  *)
(* DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL   *)
(* DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE    *)
(* GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS        *)
(* INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER *)
(* IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR      *)
(* OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN  *)
(*IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.                         *)
(************************************************************************)
(*
  Module:	    Evaluator (EVAL)

  Description:	    Evaluation support. Functions which can be called
  		    with a stream containing an evaluation request.
		    The request will be read out of the stream, processed
		    and the result written out to standard output.

  CPN Tools
*)


(* $Source: /users/cpntools/repository/cpn2000/sml/com/evalProc.sml,v $ *)

val rcsid = "$Header: /users/cpntools/repository/cpn2000/sml/com/evalProc.sml,v 1.2.10.1 2006/08/03 20:15:06 mw Exp $";



import "stream.sig";
import "error.sig";
import "miscUtils.sig";
import "byteArray.sig";
import "evalProc.sig";



functor EvalProcess (structure Str : STREAM
		     structure Err : GRAMERROR
		     structure Utils : MISCUTILS
		     structure BAExt : BYTEARRAYEXT) : EVALPROCESS = struct

    val rcsid =  "$Header: /users/cpntools/repository/cpn2000/sml/com/evalProc.sml,v 1.2.10.1 2006/08/03 20:15:06 mw Exp $";
			     
    structure Stream = Str;

    (* Probably shouldn't be here, but it makes it easier to
     * provide useful error messages for the user. *)
    exception CPN'Error of string

    (* Encoding of Compiler Errors *)
    val PARSE_ERROR = 50;
    val CPN_ERROR = 51;
    val UNEXPECTED_ERROR = 52;

    val std_out = TextIO.stdOut;
    val flush_out = TextIO.flushOut;
    val output = TextIO.output;
    val output1 = TextIO.output1;
    val open_string = TextIO.openString;
    val use_stream = Compiler.Interact.useStream;

    fun exn_code e = 
	case (exnName e) of
	    "Compile"   => (PARSE_ERROR)
          | "Syntax"	=> (PARSE_ERROR)
	  | "Abort"	=> (PARSE_ERROR)
	  | "CPN'Stop"	=> (CPN_ERROR)
	  | "CPN'Error"	=> ((output (std_out, 
				     ("Unhandled CPN'Error Exception:\n "^
				      (case e of
					   CPN'Error s => s 
					     | _ => ""))));CPN_ERROR)
	  | name 	=> (output (std_out, 
				    ("Unhandled Exception: "^name^
				     (if (exnMessage e) <> name
					  then "\n  with message: "^(exnMessage e)
                                else "")^"\n"^(concat(List.map (fn s => ("\t"^s^"\n"))
                                               (SMLofNJ.exnHistory e)))));
		     	    UNEXPECTED_ERROR);

    fun genericProcess evalFn ins =
    let 
	val len = Stream.getInteger ins;
	val ba = ByteArray.array(len,0);
	val _ = Stream.getBytes ins (ba,0,len);
	val inps = BAExt.byteArrayToString ba;

	val _ = Err.debug("read source code; length = "^(Int.toString (size inps)));

	fun myHandler () = 
	    (output (std_out, "Interrupt");
	     output1(std_out,(chr UNEXPECTED_ERROR));
	     output1(std_out,(chr 2)));

	fun eval () = 
	    (evalFn(open_string inps);
	     output1(std_out, (chr 1)))
	    handle e => (output1(std_out,(chr (exn_code e)));
			 output1(std_out,(chr 2)));
    in 
	(Utils.handleInterrupt eval myHandler);
	flush_out std_out
    end;

    val process = genericProcess use_stream;

    val stubResponse = 
	(fn str => 
	 output(std_out, "Sorry, cannot evaluate code.\n"));

    val processStub = genericProcess stubResponse;


end; (* Functor EvalProcess *)

