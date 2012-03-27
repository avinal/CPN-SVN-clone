package org.cpntools.grader.model.btl.model;

import java.util.Set;

import org.cpntools.accesscpn.engine.highlevel.HighLevelSimulator;
import org.cpntools.accesscpn.engine.highlevel.instance.Instance;
import org.cpntools.accesscpn.model.PetriNet;
import org.cpntools.accesscpn.model.Transition;
import org.cpntools.grader.model.NameHelper;

/**
 * @author michael
 */
public class Times implements Guide {
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + count;
		result = prime * result + (g == null ? 0 : g.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) { return true; }
		if (obj == null) { return false; }
		if (!(obj instanceof Times)) { return false; }
		final Times other = (Times) obj;
		if (count != other.count) { return false; }
		if (g == null) {
			if (other.g != null) { return false; }
		} else if (!g.equals(other.g)) { return false; }
		return true;
	}

	private final int count;
	private final Guide g;

	public Times(final int count, final Guide g) {
		this.count = count;
		this.g = g;
	}

	public int getCount() {
		return count;
	}

	public Guide getG() {
		return g;
	}

	@Override
	public String toString() {
		return count + " * (" + g + ")";
	}

	@Override
	public Set<Instance<org.cpntools.accesscpn.model.Transition>> force(
	        final Set<Instance<org.cpntools.accesscpn.model.Transition>> candidates, final PetriNet model,
	        final NameHelper names) {
		return g.force(candidates, model, names);
	}

	@Override
	public Guide progress(final Instance<Transition> ti, final PetriNet model, final HighLevelSimulator simulator,
	        final NameHelper names) {
		final Guide newg = g.progress(ti, model, simulator, names);
		if (g == newg) { return this; }
		if (count == 1) { return newg; }
		if (newg == null) {
			if (count == 2) { return g; }
			return new Times(count - 1, g);
		}
		return new And(newg, new Guard(newg, new Times(count - 1, g)));
	}
}