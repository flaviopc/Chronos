/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Renan
 */
public class Posicao {
    private String numero;
    private String posicao;
    
    public Posicao(){

    }
    
    public Posicao(String numero,String posicao){
        this.numero=numero;
        this.posicao=posicao;
    }

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return the posicao
     */
    public String getPosicao() {
        return posicao;
    }

    /**
     * @param posicao the posicao to set
     */
    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }
    
    
}
