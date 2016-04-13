package Dominio;

public class CtrlHetesim {
	
	Graf g;
	HeteSim het;
	
	void setGraph(Graf g) {
		this.g = g;
		het.setGraph(g);
	}
	
	public Result searchPathThreshhold(final Graf g, final Float threshold, final Path p){
		return new Result(g,threshold,het.getHeteSim(p),p);	
	}
	
	public Result searchPath(final Graf g, final Path p){
		return searchPathThreshhold(g,0.f,p);	
	}
	
	public Result searchPathNodeThreshhold(final Graf g, final Float threshold, final Path p, final Node n){
		return new Result(g,threshold,het.getHeteSim(p,n),p,n);	
	}
	
	public Result searchPathNode(final Graf g, final Path p, final Node n){
		return searchPathNodeThreshhold(g,0.f,p,n);	
	}	
	
	public Result searchPathNodeNodeThreshhold(final Graf g, final Float threshold, final Path p, final Node n1, final Node n2){
		return new Result(g,threshold,het.getHeteSim(p,n1,n2),p,n1,n2);	
	}
	
	public Result searchPathNodeNode(final Graf g, final Path p, final Node n1, final Node n2){
		return searchPathNodeThreshhold(g,0.f,p,n1,n2);	
	}
	
	
	
}
