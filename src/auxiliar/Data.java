
/**
 *
 * @author ayrton monier
 */
package auxiliar;

import java.util.*;
import java.text.*;

import java.text.SimpleDateFormat;


public class Data {

    //VARIÁVEIS staticas STRING (formato de datas)
    public static final String AAAA_MM_DD_HH_MM_SS  = "aaaa_mm_dd_hh_mm_ss";
    public static final String DD_MM_AAAA  = "dd-mm-aaaa";
    public static final String AAAA_MM_DD  = "aaaa-mm-dd";
    public static final String DIA_MES_ANO = "dia-mes-ano";
    public static final String DIA_MES     = "dia-mes";
    public static final String MES_ANO     = "mes-ano";

    //VARIÁVEIS staticas INT (tratadas)
    public static int s_diaSemana;
    public static int s_ano;

    //VARIÁVEIS STATICAS STRING
    public static String s_dia;//dia tratado
    public static String s_mes;//mes tratado
    public static String s_hora;//hora tratado
    public static String s_minuto;//minuto tratado
    public static String s_segundo;//segundos tratado


    public static String s_diaSemanalPorExtenso;
    public static String s_mesPorExtenso;
    public static String s_horaAtualHHMMSS;
    public static String s_horaAtualHHMM;
    public static String s_dataAtualDDMMAAAA;
    public static String s_dataAtualDDMMAAParaJFormattedTextFild;
    public static String s_dataAtualPorExtenso;
    public static String s_dataAtualAAAAMMDDParaBD;
    public static String s_dataHoraAtualDDMMAAAAHHMMSS;
    public static String s_dataAtualAAAAMMDDHHMMSSParaBD;

    public static String s_diaOntem;
    public static String s_dataOntemDDMMAAAA;

    private static Date s_dataHora;

    public static void atualizaDataHora(){

        s_dataHora = new Date();

        //DETERMINAÇÃO DO ANO ATUAL
        //obs: soma-se com 1900 pois o ano começa em 111
        s_ano = s_dataHora.getYear()+1900;

        s_dia = pegaValorFormatado(s_dataHora.getDate(), "dia");
        s_diaSemanalPorExtenso = pegaValorFormatado(s_dataHora.getDay(), "dia-semanal");

        s_mes = pegaValorFormatado(s_dataHora.getMonth(), "mes");
        s_mesPorExtenso = pegaValorFormatado(s_dataHora.getMonth(), "mes-extenso");

        s_hora = pegaValorFormatado(s_dataHora.getHours(), "hora");

        s_minuto = pegaValorFormatado(s_dataHora.getMinutes(), "minuto");

        s_segundo = pegaValorFormatado(s_dataHora.getSeconds(), "segundo");

        //hora formatada em HH:MM:SS
        s_horaAtualHHMMSS = s_hora+":"+s_minuto+":"+s_segundo;

        //pega hora atual em HH:MM
        s_horaAtualHHMM = s_hora+":"+s_minuto;

        //data atual em DD-MM-AAAA
        s_dataAtualDDMMAAAA = s_dia+"-"+s_mes+"-"+s_ano;

        //data hora atual em ddmmaa hhmmss
        s_dataHoraAtualDDMMAAAAHHMMSS = s_dataAtualDDMMAAAA+" "+s_horaAtualHHMMSS;

        //pega a data atual para um objeto JFormatedTextField em DDMMAAAA (sem separador)
        s_dataAtualDDMMAAParaJFormattedTextFild = s_dia+""+s_mes+""+s_ano;

        //pega data atual para guardar no banco de dados em AAAA/MM/DIA
        s_dataAtualAAAAMMDDParaBD = s_ano+"-"+s_mes+"-"+s_dia;

        //pega data atual para guardar no banco de dados em AAAA/MM/DIA HHMMSS
        s_dataAtualAAAAMMDDHHMMSSParaBD = s_ano+"-"+s_mes+"-"+s_dia+" "+s_hora+":"+s_minuto+":"+s_segundo;

        //pega a data atual por extenso em ex: sábado, 22 de Janeiro de 2011
        s_dataAtualPorExtenso = s_diaSemanalPorExtenso+", "+s_dia+Avisos.TEXTO_DATA_DE+s_mesPorExtenso+Avisos.TEXTO_DATA_DE+s_ano;

    }

    //validação de s_comprimento da tela de entrada
    public static String comprimentacao(){

            String l_comprimento = null;
            int hora = Integer.parseInt(s_hora);

            //se hora for entre 0 e 12...
            if(hora >= 0 && hora < 12)
                   l_comprimento = Avisos.TEXTO_BOM_DIA;
            else if(hora >= 12 && hora < 18)
                   l_comprimento = Avisos.TEXTO_BOA_TARDE;
            else if(hora >= 18)
                   l_comprimento = Avisos.TEXTO_BOA_NOITE;

            return l_comprimento;

    }//fim método configuraTurno


   //ajusta data para o formato AAAA-MM-DD
    public static String ajustaDataParaAAAAMMDD(Date l_diaMesAno){

        DateFormat l_dataAnoMesDia = new SimpleDateFormat("yyyy-MM-dd");

        String l_anoMesDia = l_dataAnoMesDia.format(l_diaMesAno);

        return l_anoMesDia;

    }

   //ajusta data para o formato DD-MM-AAAA
    public static String ajustaDataParaDDMMAAAA(String l_aaaaMMdd){

        DateFormat l_anoMesDiaFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat l_diaMesAnoFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date l_DateAnoMesDia = null;
        String l_ddMMaaaa = null;

            try{
                l_DateAnoMesDia = (Date)l_anoMesDiaFormat.parse(l_aaaaMMdd);

                l_ddMMaaaa = l_diaMesAnoFormat.format(l_DateAnoMesDia);

            }catch(Exception e){
                System.err.println("Erro ao tentar transformar data para dd-mm-aaaa.    \nErro: "+e);
            }

        return l_ddMMaaaa;

    }

    //ajusta data para o formato DD-MM-AAAA
    public static String ajustaDataDoBDparaDDMMAAAA(String l_aaaaMMddHHmmSS){

        DateFormat l_anoMesDiaHoraMinSegFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat l_diaMesAnoFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date l_DateAnoMesDiaHoraMinSeg = null;
        String l_ddMMaaaa = null;

            try{
                l_DateAnoMesDiaHoraMinSeg = (Date)l_anoMesDiaHoraMinSegFormat.parse(l_aaaaMMddHHmmSS);

                l_ddMMaaaa = l_diaMesAnoFormat.format(l_DateAnoMesDiaHoraMinSeg);

            }catch(Exception e){
                System.err.println("Erro ao tentar transformar data do BD para dd-mm-aaaa.    \nErro: "+e);
            }

        return l_ddMMaaaa;

    }

   //ajusta data para o formato DD-MM-AAAA
    public static String ajustaDataParaDDMMAAAAHHMMSS(String l_aaaaMMddHHmmSS){

        DateFormat l_aaaaMMddHHmmSSFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat l_ddMMaaaaHHmmSSFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date l_aaaaMMddHHmmSSDate = null;
        String l_ddMMaaaaHHmmSS = null;

            try{
                l_aaaaMMddHHmmSSDate = (Date)l_aaaaMMddHHmmSSFormat.parse(l_aaaaMMddHHmmSS);

                l_ddMMaaaaHHmmSS = l_ddMMaaaaHHmmSSFormat.format(l_aaaaMMddHHmmSSDate);


            }catch(Exception e){
                System.err.println("Erro ao tentar transformar data para dd-mm-aaaa-hh-mm-ss.    \nErro: "+e);
            }

        return l_ddMMaaaaHHmmSS;

    }

    //pega data tipo date
    public static Date pegaDataTipoDateDDMMAAAA(String l_data, String l_tipo){

        String l_dataDDMMAAAA = null;
        Date l_dataTipoDate = null;
        DateFormat l_dataFormatada = new SimpleDateFormat("dd-MM-yyyy");

        try{

            if(l_tipo.equals(AAAA_MM_DD))
                l_dataDDMMAAAA = ajustaDataParaDDMMAAAA(l_data);
            else if(l_tipo.equals(DD_MM_AAAA))
                l_dataDDMMAAAA = l_data;
            else if(l_tipo.equals(AAAA_MM_DD_HH_MM_SS))
                l_dataDDMMAAAA = ajustaDataDoBDparaDDMMAAAA(l_data);

            l_dataTipoDate = (Date)l_dataFormatada.parse(l_dataDDMMAAAA);
        }
        catch(Exception e){
            System.err.println("Erro ao tentar converter data de String para Date.  \nErro: "+e);
        }

        return l_dataTipoDate;

    }

    public static String pegaDataPorExtenso(Date l_diaMesAno, String l_tipo){

        int l_dia = l_diaMesAno.getDate();
        int l_mes = l_diaMesAno.getMonth();
        int l_ano = l_diaMesAno.getYear()+1900;
        
        String l_mesExtenso = pegaValorFormatado(l_mes, "mes-extenso");

        String l_dataPorExtenso = null;

            if(l_tipo.equals(MES_ANO))
                l_dataPorExtenso = l_mesExtenso+Avisos.TEXTO_DATA_DE+l_ano;
            else if(l_tipo.equals(DIA_MES))
                 l_dataPorExtenso = l_dia+Avisos.TEXTO_DATA_DE+l_mesExtenso;
            else if(l_tipo.equals(DIA_MES_ANO))
                 l_dataPorExtenso = l_dia+Avisos.TEXTO_DATA_DE+l_mesExtenso+Avisos.TEXTO_DATA_DE+l_ano;

        return l_dataPorExtenso;

    }

   //ajusta data para o formato DD-MM-AAAA
    public static int pegaHoraOuMinuto(String l_hhMMss, String l_horaOuMinuto){
    
        int l_valor = 0;

            if(l_hhMMss == null){
                l_valor = 0;

            }else{

                DateFormat l_hhMMssFormat = new SimpleDateFormat("HH:mm:ss");
                Date l_hhMMssDate = null;


                    try{
                        l_hhMMssDate = (Date)l_hhMMssFormat.parse(l_hhMMss);

                            if(l_horaOuMinuto.equals("hora"))
                                l_valor = l_hhMMssDate.getHours();
                            else if (l_horaOuMinuto.equals("minuto"))
                                l_valor = l_hhMMssDate.getMinutes();


                    }catch(Exception e){
                        l_valor = 0;
                        System.err.println("Erro ao tentar pegar hora ou minuto.    \nErro: "+e);
                    }

//                System.err.println(l_horaOuMinuto+" : "+l_valor);

            }

    return l_valor;

    }

    public static String pegaIntervaloTempo(String l_dataHoraAtual, String l_dataHoraEnvioRecado){

        DateFormat l_dataHoraFormatada = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String l_diferencaDatas = "";
        String l_horaFormat;
        String l_minutosFormat;
        String l_diaFormat;
        String l_diaSemanaExt;
        int l_anoPostagem = 1900;
        String l_mesExt;
        String l_quando = null;
        int l_segundosAtual = 0;
        int l_segundosPostado = 0;
        int l_minutoAtual = 0;
        int l_horaAtual = 0;
        int l_diaAtual = 0;
        int l_anosPostado = 0;
        int l_anoAtual = 1900;
        int l_mesAtual = 0;
        int l_minutosPostado = 0;

        try{

            Date l_dataAtu = (Date)l_dataHoraFormatada.parse(l_dataHoraAtual);

            l_mesAtual = l_dataAtu.getMonth();
            l_diaAtual = l_dataAtu.getDate();
            l_horaAtual = l_dataAtu.getHours();
            l_minutoAtual = l_dataAtu.getMinutes();
            l_segundosAtual = l_dataAtu.getSeconds();
            l_anoAtual += l_dataAtu.getYear();


            Date l_dataEnvRec = (Date)l_dataHoraFormatada.parse(l_dataHoraEnvioRecado);

            long segundos = (l_dataAtu.getTime() - l_dataEnvRec.getTime()) / 1000;

            int semanas = (int)Math.floor(segundos / 604800);

            segundos -= semanas * 604800;

            int dias = (int)Math.floor(segundos / 86400);

            segundos -= dias * 86400;

            int horas = (int)Math.floor(segundos / 3600);

            segundos -= horas * 3600;

            int minutos = (int)Math.floor(segundos / 60);

            segundos -= minutos * 60;

            l_horaFormat = pegaValorFormatado(l_dataEnvRec.getHours(), "hora");

            l_minutosPostado = l_dataEnvRec.getMinutes();

            l_minutosFormat = pegaValorFormatado(l_minutosPostado, "minuto");

            l_diaFormat = pegaValorFormatado(l_dataEnvRec.getDate(), "dia");

            l_diaSemanaExt = pegaValorFormatado(l_dataEnvRec.getDay(), "dia-semanal").toLowerCase();

            l_mesExt = pegaValorFormatado(l_dataEnvRec.getMonth(), "mes-extenso").toLowerCase();

            l_anoPostagem += l_dataEnvRec.getYear();


            if(semanas <= 0 && dias <= 0 && horas <= 0 && minutos <= 00 && segundos <= 10)
                l_diferencaDatas = Avisos.TEXTO_DATA_AGORA+"·("+l_horaFormat+":"+l_minutosFormat+")";

            else if(semanas <= 0 && dias <= 0 && horas <= 0 && minutos <= 00 && (segundos > 10 && segundos <= 59))
                l_diferencaDatas = Avisos.TEXTO_DATA_HA+" ± "+segundos+" "+Avisos.TEXTO_DATA_SEGUNDOS+"·("+l_horaFormat+":"+l_minutosFormat+")";

            else if(semanas <= 0 && dias <= 0 && horas <= 0 && minutos <= 59 && segundos <= 59)
                l_diferencaDatas =  Avisos.TEXTO_DATA_HA+" ± "+minutos+" "+Avisos.TEXTO_DATA_MINUTOS+" ("+l_horaFormat+":"+l_minutosFormat+")";

            else if(semanas <= 0 && dias <= 0 && horas <= 23 && minutos <= 59 && segundos <= 59){

                l_segundosPostado = ((((l_horaAtual*60)+l_minutoAtual)*60)+l_segundosAtual) - ((((horas*60)+minutos)*60)+(int)segundos);

                    if(l_segundosPostado < 0)
                        l_quando = Avisos.TEXTO_DATA_ONTEM;
                    else
                        l_quando = Avisos.TEXTO_DATA_HOJE;
                                
                l_diferencaDatas = l_quando+"·("+l_diaSemanaExt+"·"+l_horaFormat+":"+l_minutosFormat+")";

            }

            else if(semanas <= 0 && dias <= 6 && horas <= 23 && minutos <= 59 && segundos <= 59){

                //caso um dia
                if(semanas <= 0 && dias == 1 && horas <= 23 && minutos <= 59 && segundos <= 59){

                    l_segundosPostado = ((((l_horaAtual*60)+l_minutoAtual)*60)+l_segundosAtual) - ((((horas*60)+minutos)*60)+(int)segundos);

                        if(l_segundosPostado < 0)
                            l_quando = Avisos.TEXTO_DATA_ANTEONTEM;
                        else
                            l_quando = Avisos.TEXTO_DATA_ONTEM;


                    l_diferencaDatas = l_quando+"·"+l_diaSemanaExt+"·("+l_horaFormat+":"+l_minutosFormat+")";


                }

                //caso faça mais de um dia (25h)
                else if(semanas <= 0 && dias > 1 && horas <= 23 && minutos <= 59 && segundos <= 59){

                    l_segundosPostado = (((((l_diaAtual*24)*60)+l_minutoAtual)*60)+l_segundosAtual) - (((((dias*24)*60)+minutos)*60)+(int)segundos);

                        if(l_segundosPostado < 0)
                            l_quando = l_diaSemanaExt+"·"+l_diaFormat+"·"+l_mesExt;
                        else
                            l_quando = l_diaSemanaExt;

                    l_diferencaDatas = l_quando+"·("+l_horaFormat+":"+l_minutosFormat+")";
                }
            }

            else{

                   if(semanas < 53){

                        l_anosPostado = l_anoPostagem - l_anoAtual;

//                        System.err.println("Anos postado: "+l_anosPostado);

                            if(l_anosPostado < 0)
                                l_quando = l_diaSemanaExt+"·"+l_diaFormat+"·"+l_mesExt+"·"+l_anoPostagem;
                            else
                                l_quando = l_diaSemanaExt+"·"+l_diaFormat+"·"+l_mesExt;
                    }else
                        l_quando = l_diaSemanaExt+"·"+l_diaFormat+"·"+l_mesExt+"·"+l_anoPostagem;


                 l_diferencaDatas = l_quando+"·("+l_horaFormat+":"+l_minutosFormat+")";

            }

//            System.out.println("As duas datas tem "+semanas+" semanas, "+dias+" dias, "+horas+" horas, "+minutos+" minutos e "+segundos+" segundos de diferença");

        }
        catch(ParseException e){
            System.err.println("Erro ao tentar pegar intervalo de datas.    \nErro:"+e);

        }

            return l_diferencaDatas;
    }




    private static String pegaValorFormatado(int l_valor, String l_tipoValor){


        String l_valorRetorno = null;


        if(l_tipoValor.equals("dia")){

            //TRATAMENTO DIAS
            //dia formatado (01, 02, 03... 09. e não 1, 2, 3... 9)
            switch(l_valor){

                case 1: l_valorRetorno = "01";break;
                case 2: l_valorRetorno = "02";break;
                case 3: l_valorRetorno = "03";break;
                case 4: l_valorRetorno = "04";break;
                case 5: l_valorRetorno = "05";break;
                case 6: l_valorRetorno = "06";break;
                case 7: l_valorRetorno = "07";break;
                case 8: l_valorRetorno = "08";break;
                case 9: l_valorRetorno = "09";break;

                default:
                    l_valorRetorno = String.valueOf(l_valor);
            }

        }


        else if(l_tipoValor.equals("mes")){
                //TRATAMENTO MESES
                //mes atual formatado (01, 02, 03... 09. e não 1, 2, 3... 9)
                switch(l_valor){

                    case 0: l_valorRetorno = "01";break;
                    case 1: l_valorRetorno = "02";break;
                    case 2: l_valorRetorno = "03";break;
                    case 3: l_valorRetorno = "04";break;
                    case 4: l_valorRetorno = "05";break;
                    case 5: l_valorRetorno = "06";break;
                    case 6: l_valorRetorno = "07";break;
                    case 7: l_valorRetorno = "08";break;
                    case 8: l_valorRetorno = "09";break;
                    case 9: l_valorRetorno = "10";break;
                    case 10: l_valorRetorno = "11";break;
                    case 11: l_valorRetorno = "12";break;
                }
        }


        else if(l_tipoValor.equals("hora")){
                //TRATAMENTO HORAS
                //minuto formatado (01, 02, 03... 09. e não 1, 2, 3... 9)
                switch(l_valor){

                    case 0: l_valorRetorno = "00";break;
                    case 1: l_valorRetorno = "01";break;
                    case 2: l_valorRetorno = "02";break;
                    case 3: l_valorRetorno = "03";break;
                    case 4: l_valorRetorno = "04";break;
                    case 5: l_valorRetorno = "05";break;
                    case 6: l_valorRetorno = "06";break;
                    case 7: l_valorRetorno = "07";break;
                    case 8: l_valorRetorno = "08";break;
                    case 9: l_valorRetorno = "09";break;

                    default:
                        l_valorRetorno = String.valueOf(l_valor);
                }
        }


        else if(l_tipoValor.equals("minuto")){
                //TRATAMENTO MINUTOS
                //minuto formatado (01, 02, 03... 09. e não 1, 2, 3... 9)
                switch(l_valor){

                    case 0: l_valorRetorno = "00";break;
                    case 1: l_valorRetorno = "01";break;
                    case 2: l_valorRetorno = "02";break;
                    case 3: l_valorRetorno = "03";break;
                    case 4: l_valorRetorno = "04";break;
                    case 5: l_valorRetorno = "05";break;
                    case 6: l_valorRetorno = "06";break;
                    case 7: l_valorRetorno = "07";break;
                    case 8: l_valorRetorno = "08";break;
                    case 9: l_valorRetorno = "09";break;

                    default:
                        l_valorRetorno = String.valueOf(l_valor);
                }
        }


        else if(l_tipoValor.equals("segundo")){
                //TRATAMENTO SEGUNDOS
                //minuto formatado (01, 02, 03... 09. e não 1, 2, 3... 9)
                switch(l_valor){

                    case 0: l_valorRetorno = "00";break;
                    case 1: l_valorRetorno = "01";break;
                    case 2: l_valorRetorno = "02";break;
                    case 3: l_valorRetorno = "03";break;
                    case 4: l_valorRetorno = "04";break;
                    case 5: l_valorRetorno = "05";break;
                    case 6: l_valorRetorno = "06";break;
                    case 7: l_valorRetorno = "07";break;
                    case 8: l_valorRetorno = "08";break;
                    case 9: l_valorRetorno = "09";break;

                    default:
                        l_valorRetorno = String.valueOf(l_valor);
                }
        }


        else if(l_tipoValor.equals("dia-semanal")){
                //TRATAMENTO DIA DA SEMANA POR EXTENSO
                //dia formatado (0 = DOMINGO, 1 = SEGUNDA...)
                switch(l_valor){

                    case 0: l_valorRetorno = Avisos.TEXTO_DATA_DOMINGO;break;
                    case 1: l_valorRetorno = Avisos.TEXTO_DATA_SEGUNDA;break;
                    case 2: l_valorRetorno = Avisos.TEXTO_DATA_TERCA;break;
                    case 3: l_valorRetorno = Avisos.TEXTO_DATA_QUARTA;break;
                    case 4: l_valorRetorno = Avisos.TEXTO_DATA_QUINTA;break;
                    case 5: l_valorRetorno = Avisos.TEXTO_DATA_SEXTA;break;
                    case 6: l_valorRetorno = Avisos.TEXTO_DATA_SABADO;break;
                }
        }


        else if(l_tipoValor.equals("mes-extenso")){
                //TRATAMENTO MêS POR EXTENSO
                //dia formatado (0 = JANEIRO, 1 = FEVEREIRO...)
                 switch(l_valor){

                    case 0: l_valorRetorno = Avisos.TEXTO_DATA_JANEIRO;break;
                    case 1: l_valorRetorno = Avisos.TEXTO_DATA_FEVEREIRO;break;
                    case 2: l_valorRetorno = Avisos.TEXTO_DATA_MARCO;break;
                    case 3: l_valorRetorno = Avisos.TEXTO_DATA_ABRIL;break;
                    case 4: l_valorRetorno = Avisos.TEXTO_DATA_MAIO;break;
                    case 5: l_valorRetorno = Avisos.TEXTO_DATA_JUNHO;break;
                    case 6: l_valorRetorno = Avisos.TEXTO_DATA_JULHO;break;
                    case 7: l_valorRetorno = Avisos.TEXTO_DATA_AGOSTO;break;
                    case 8: l_valorRetorno = Avisos.TEXTO_DATA_SETEMBRO;break;
                    case 9: l_valorRetorno = Avisos.TEXTO_DATA_OUTUBRO;break;
                    case 10:l_valorRetorno = Avisos.TEXTO_DATA_NOVEMBRO;break;
                    case 11:l_valorRetorno = Avisos.TEXTO_DATA_DEZEMBRO;break;

                }
        }

        return l_valorRetorno;

    }



}


