package model;

public abstract class Expression implements Calculatable, Equation {
	public abstract Expression derivative() throws Exception;
	public abstract Expression integrate() throws Exception;
	public abstract double calc() throws Exception;
}
