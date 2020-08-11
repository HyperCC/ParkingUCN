package cl.ucn.disc.pdis.appparkingucn;

import cl.ucn.disc.pdis.appparkingucn.zeroice.ZeroIce;
import cl.ucn.disc.pdis.simplescraper.zeroice.model.ContratosPrx;
import cl.ucn.disc.pdis.simplescraper.zeroice.model.Persona;


public class Communicator {

    private final ZeroIce ICE_CONNECTION;

    private static ContratosPrx operator;

    public Communicator() {

        ICE_CONNECTION = new ZeroIce();
        ICE_CONNECTION.start();

        operator = ICE_CONNECTION.getContratosPrx();
    }

    public Persona obtenerPersona (String rut){

        try{
            return operator.obtenerPersona(rut);
        }catch(Exception e){
            return null;
        }
    }
}