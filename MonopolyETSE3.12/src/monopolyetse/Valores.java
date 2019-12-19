
package monopolyetse;


public class Valores {
    
    public final static String[] SOLARES={"Muimenta","Carnota","Allariz","Ribadavia",
    "Viveiro","Monforte","Riveira","Porrino","Pontedeume","Vilalba","Baiona",
    "Tui","Verin","Vilagarcia","Ferrol","Pontevedra","Sanxenxo","Lugo","Ourense",
    "Santiago","Coruna","Vigo"}; // Tamaño en pantalla 18
    
    public final static  String NEGRO="\033[0;40;30m";
    public final static  String ROJO="\033[1;40;31m";
    public final static  String MARRON="\033[0;40;31m";
    public final static String VERDE="\033[0;40;32m";
    public final static String AMARILLO="\033[1;40;33m";
    public final static String NARANJA="\033[0;40;33m";
    public final static String AZUL="\033[0;40;34m";
    public final static String MAGENTA="\033[0;40;35m";
    public final static String CYAN="\033[0;40;36m";
    
    public final static long VALORGRUPO1=1200;
    public final static long VALORGRUPO2=(long)(VALORGRUPO1*1.3);
    public final static long VALORGRUPO3=(long)(VALORGRUPO2*1.3);
    public final static long VALORGRUPO4=(long)(VALORGRUPO3*1.3);
    public final static long VALORGRUPO5=(long)(VALORGRUPO4*1.3);
    public final static long VALORGRUPO6=(long)(VALORGRUPO5*1.3);
    public final static long VALORGRUPO7=(long)(VALORGRUPO6*1.3);
    public final static long VALORGRUPO8=(long)(VALORGRUPO7*1.3);
    public final static long TOTAL=VALORGRUPO1+VALORGRUPO2+VALORGRUPO3+VALORGRUPO4+VALORGRUPO5+VALORGRUPO6+VALORGRUPO7+VALORGRUPO8;
    public final static long FORTUNAINICIAL=TOTAL/3;
    public final static long PASOSALIDA=TOTAL/22;
    public final static long VALTRANSPORTE=PASOSALIDA;
    public final static long VALSERVICIO=(long)(0.75*PASOSALIDA);
    public final static long VALCAPITAL=PASOSALIDA;
    public final static long VALLUJO=PASOSALIDA/2;
    public final static long SALIRCARCEL=PASOSALIDA/4;
}
