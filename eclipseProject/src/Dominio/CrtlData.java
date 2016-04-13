package Dominio;

import java.util.ArrayList;

import Persistencia.CargarGuardarPath;
import Persistencia.CargarGuardarResultado;

import java.nio.file.Path;

public class CrtlData {
	
	CrtlDataGraph CtrlGraph;
	CargarGuardarResultado CGR;
	CargarGuardarPath CGP;
	
	private Pair<Graf,ArrayList<Result>> GraphAndResults;
	private ArrayList<Dominio.Path> Paths;
	private Path filePath;
	
}
