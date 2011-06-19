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
  Module:           Probability Distribution Functions Library

  Description:      Probability Distribution Functions

  Created by:       Theo van Drimmelen

  Date:             January 1998
*)

structure ProbLibDist :
    sig
	exception Bernoulli;
	val bernoulli : real -> int;

	exception Binomial;
	val binomial : int * real -> int;

	exception Uniform;
	val uniform : real * real -> real;

	exception Normal;
	val normal : real * real -> real;

	exception Chisq;	
	val chisq : int -> real;

	exception Student;
	val student : int -> real;

	exception Exponential;
	val exponential : real -> real;

	exception Poisson;
	val poisson : real -> int;

	exception Erlang;
	val erlang : int * real -> real;
	
    end = 

struct

    exception Bernoulli;
    fun bernoulli (p) = if p >= 0.0 andalso p <= 1.0
                     then if problibrandom() < p then 1 else 0
                     else raise Bernoulli;

    exception Binomial;
    fun binomial (n,p) = if n >= 1 andalso p >= 0.0 andalso p <= 1.0
                      then bernoulli(p) + (if n = 1 then 0 else binomial(n-1,p))
                      else raise Binomial;

    exception Uniform;
    fun uniform (a,b) = if a <= b then (b-a)*problibrandom() + a
			else raise Uniform;

    exception Normal;
    local 
	val 
	    b = ref(false) and
	    y2 = ref(0.0) and
	    pi = 4.0*Math.atan(1.0);

    in 
	fun normal (n:real,s:real) =
	if s >= 0.0
	    then if !b 
		     then (b := false;
			   n+Math.sqrt(s)* !y2)
		 else 
		     let 
			 val 
			     sub1 = Math.sqrt(~2.0*Math.ln(problibrandom())) and
			     sub2 = 2.0*pi*problibrandom();
		     in 
			 b := true;
			 y2 := sub1*Math.sin(sub2);
			 n+Math.sqrt(s)*sub1*Math.cos(sub2)
		     end
	else 
	    raise Normal
    end;

    exception Chisq;
    fun chisq (n) = 
	let 
	    val temp = normal(0.0,1.0);
	in 
	    if n >= 1 
		then temp*temp + 
		    (if n=1 then 0.0
		     else chisq(n-1))
	    else raise Chisq
	end;
	
    exception Student;
    fun 
	student (n) = 
	let 
	    fun sum (n) = 
		let 
		    val sqr = normal(0.0,1.0);
		in 
		    sqr*sqr + 
		    (if n=1 then 0.0 
		     else sum(n-1))
		end;
	in 
	    if n >= 1 then normal(0.0,1.0)/Math.sqrt(sum(n))
	    else raise Student
	end;

    exception Exponential;
    fun exponential (l:real) = if l > 0.0 
				    then Math.ln(1.0-problibrandom())/ ~l
				else raise Exponential;

    exception Poisson;
    fun poisson (i) = 
	let 
	    fun event (t,i)= 
		if t>0.0 
		    then 1+event(t-exponential(i),
					 i) 
		else 0;
	in if i > 0.0 then event(1.0,i) - 1
	   else raise Poisson 
	end;
	
    exception Erlang;
    fun erlang (n,l) = 
	if n >=1 andalso l > 0.0
	    then exponential(l) + 
		(if n=1 then 0.0 else erlang(n-1,l))
	else raise Erlang;
end;

open ProbLibDist;