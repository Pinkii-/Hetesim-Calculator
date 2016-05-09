package Dominio;

/**
 * 
 * @author Gonzalo Diez
 * 
 */

public class CtrlSearch {

	Graph g = null;
	HeteSim het = new HeteSim();

	public void setGraph(Graph g) {
		this.g = g;
		het.setGraph(g);
	}

	public Result searchPathThreshhold(final Graph g, final Float threshold, final Path p) throws PathException {
		return new Result(g, threshold, het.getHeteSim(p), p);
	}

	public Result searchPath(final Graph g, final Path p) throws PathException {
		return searchPathThreshhold(g, 0.f, p);
	}

	public Result searchPathNodeThreshhold(final Graph g, final Float threshold, final Path p, final Node n)
			throws PathException {
		return new Result(g, threshold, het.getHeteSim(p, n), p, n);
	}

	public Result searchPathNode(final Graph g, final Path p, final Node n) throws PathException {
		return searchPathNodeThreshhold(g, 0.f, p, n);
	}

	public Result searchPathNodeNodeThreshhold(final Graph g, final Float threshold, final Path p, final Node n1,
			final Node n2) throws PathException {
		return new Result(g, threshold, het.getHeteSim(p, n1, n2), p, n1, n2);
	}

	public Result searchPathNodeNode(final Graph g, final Path p, final Node n1, final Node n2) throws PathException {
		return searchPathNodeNodeThreshhold(g, 0.f, p, n1, n2);
	}

}
