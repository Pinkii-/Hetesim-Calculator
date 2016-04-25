package Dominio;

/**
 * 
 * @author Gonzalo Diez
 * 
 */

public class CtrlSearch {

	Graf g = null;
	HeteSim het = new HeteSim();

	public void setGraph(Graf g) {
		this.g = g;
		het.setGraph(g);
	}

	public Result searchPathThreshhold(final Graf g, final Float threshold, final Path p) throws PathException {
		return new Result(this.g, threshold, het.getHeteSim(p), p);
	}

	public Result searchPath(final Graf g, final Path p) throws PathException {
		return searchPathThreshhold(this.g, 0.f, p);
	}

	public Result searchPathNodeThreshhold(final Graf g, final Float threshold, final Path p, final Node n)
			throws PathException {
		return new Result(this.g, threshold, het.getHeteSim(p, n), p, n);
	}

	public Result searchPathNode(final Graf g, final Path p, final Node n) throws PathException {
		return searchPathNodeThreshhold(this.g, 0.f, p, n);
	}

	public Result searchPathNodeNodeThreshhold(final Graf g, final Float threshold, final Path p, final Node n1,
			final Node n2) throws PathException {
		return new Result(this.g, threshold, het.getHeteSim(p, n1, n2), p, n1, n2);
	}

	public Result searchPathNodeNode(final Graf g, final Path p, final Node n1, final Node n2) throws PathException {
		return searchPathNodeNodeThreshhold(this.g, 0.f, p, n1, n2);
	}

}
