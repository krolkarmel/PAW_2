package com.jsfcourse.calc;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Named
@RequestScoped
//@SessionScoped
public class CalcBB {
	private String x;
	private String y;
	private String z;
	private Double result;

	@Inject
	FacesContext ctx;

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}
	
	public String getZ() {
		return z;
	}

	public void setZ(String z) {
		this.z = z;
	}

	public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}

	public String doTheMath() {
		try {
			if (x == null || x.isEmpty() || y == null || y.isEmpty() || z == null || z.isEmpty()) {
				ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wszystkie pola są wymagane", null));
				return null;
			}
			
			double y = Double.parseDouble(this.y);
			double z = Double.parseDouble(this.z);
			double x = Double.parseDouble(this.x);
			
			if (x < 5000) {
				ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Kwota musi być większa niż 5 000", null));
				return null;
			}
			
			if (y < 12) {
				ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Okres kredytowania musi być większy niz 12 miesięcy", null));
				return null;
			}
			
			if (z < 0.5 && z > 40) {
				ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Oprocentowanie powinno znajdować się w przedziale od 0.1 do 50 procent", null));
				return null;
			}
			
			result = x * z / (y * 12);
			
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
			return "showresult"; 
		} catch (Exception e) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas przetwarzania parametrów", null));
			return null;
		}
	}

	

//	public String calc_AJAX() {
//		if (doTheMath()) {
//			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Wynik: " + result, null));
//		}
//		return null;
//	}

	public String info() {
		return "info";
	}
}
