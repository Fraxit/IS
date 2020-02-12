package bean;

import java.io.Serializable;

public class ProductBean implements Serializable {


	private static final long serialVersionUID = 1L;

	private int id;
	private String nome;
	private double prezzo;
	private String descr;
	private int qtprod;
	private String imglink;

	public ProductBean () {
	}


	public String getImglink() {
		return imglink;
	}


	public void setImglink(String imglink) {
		this.imglink = imglink;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public double getPrezzo() {
		return prezzo;
	}


	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}


	public String getDescr() {
		return descr;
	}


	public void setDescr(String descr) {
		this.descr = descr;
	}


	public int getQtprod() {
		return qtprod;
	}


	public void setQtprod(int qtprod) {
		this.qtprod = qtprod;
	}

	@Override
	public boolean equals(Object other) {
		return (this.getId() == ((ProductBean) other).getId());
	}

	@Override
	public String toString() {
		return "[nome: " + nome + "][id: " + id + "][prezzo: " + prezzo + "][quantità: " + qtprod + "][descrizione: " + descr + "]";
	}

}