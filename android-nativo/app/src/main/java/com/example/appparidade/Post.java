package com.example.appparidade;

public class Post {

    private Nome nome;
    //private Capital capital;
    private String historico;

    public Nome getNome() {
        return nome;
    }


    public String getHistorico() {
        return historico;
    }

    public static class Nome {
        private String abreviado;
        public String getAbreviado() {
            return abreviado;
        }
    }
    public static class Capital {
        private String nome;
        private String governo;

        public String getNome() {
            return nome;
        }

        public String getGoverno(){
            return governo;
        }


    }
}