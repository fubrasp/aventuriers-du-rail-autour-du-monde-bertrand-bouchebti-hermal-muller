package modeles;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by bertran95u on 22/04/2017.
 */
/**
 * Classe modelisant toutes les cartes destinations et itineraires
 */
public class InitPiocheDestination {

    //On aurait aussi pu utiliser une enum
    public static ArrayList<CarteDestination> initDestinationsItineraires(){

        Map<String,Ville> villes = Jeu.getInstance().getVilles();

        Ville sydney = villes.get("sydn");

        Ville athina = villes.get("atbi");
        Ville manila = villes.get("mani");

        Ville bangkok = villes.get("bang");
        Ville tokyo = villes.get("toky");

        Ville buenosAires = villes.get("buen");

        Ville marseille = villes.get("mars");

        Ville capTown = villes.get("cape");
        Ville jakarta = villes.get("jaka");

        Ville caracas = villes.get("cara");

        Ville casablanca = villes.get("casa");
        Ville honolulu = villes.get("hono");

        Ville yakutsk = villes.get("yaku");

        Ville darEsSalaam = villes.get("dar");

        Ville djibouti = villes.get("djib");
        Ville lahore = villes.get("labo");

        Ville edinburg = villes.get("edin");
        Ville luanda = villes.get("luan");

        Ville hongKong = villes.get("hong");

        Ville hamburg = villes.get("hamb");
        Ville beijing = villes.get("beij");

        Ville lagos = villes.get("lago");

        Ville tehran = villes.get("tehr");

        Ville lima = villes.get("lima");

        Ville losAngeles = villes.get("losa");

        Ville rioDeJaneiro = villes.get("rio");

        Ville christchurch = villes.get("chri");

        Ville mexico = villes.get("mexi");

        Ville mumbai = villes.get("mumb");

        Ville newYork = villes.get("newy");

        Ville miami = villes.get("miam");
        Ville moskva = villes.get("mosk");

        Ville petropavlosk = villes.get("petr");

        Ville toamasina = villes.get("toam");

        Ville novosibirsk = villes.get("novo");
        Ville darwin = villes.get("darw");

        Ville reykjavik = villes.get("reyk");

        Ville perth = villes.get("pert");

        Ville valparaiso = villes.get("valp");

        Ville vancouver = villes.get("vanc");

        Ville winnipeg = villes.get("winn");

        Ville murmansk = villes.get("murm");

        Ville tiksi = villes.get("tiks");

        Ville cambridgeBay = villes.get("camb");

        Ville anchorage = villes.get("anch");

        Ville alQahira = villes.get("alqa");

        Ville portMeresby = villes.get("port");

        ArrayList<Ville> villesCDalZahiraSydney = new ArrayList<Ville>();
        villesCDalZahiraSydney.add(alQahira);
        villesCDalZahiraSydney.add(sydney);
        CarteDestination alZahiraSydney = new CarteDestination(19, villesCDalZahiraSydney);

        ArrayList<Ville> villesCIanchorageCambridgeReykjavikMurmanskTiksi = new ArrayList<Ville>();
        villesCIanchorageCambridgeReykjavikMurmanskTiksi.add(anchorage);
        villesCIanchorageCambridgeReykjavikMurmanskTiksi.add(cambridgeBay);
        villesCIanchorageCambridgeReykjavikMurmanskTiksi.add(reykjavik);
        villesCIanchorageCambridgeReykjavikMurmanskTiksi.add(murmansk);
        villesCIanchorageCambridgeReykjavikMurmanskTiksi.add(tiksi);
        CarteItineraire anchorageCambridgeRaykjavikMurmanskTiksi = new CarteItineraire(34, 23, 40, villesCIanchorageCambridgeReykjavikMurmanskTiksi);

        ArrayList<Ville> villesCIanchorageVancouverWinnipegCambridgeBay = new ArrayList<Ville>();
        villesCIanchorageVancouverWinnipegCambridgeBay.add(anchorage);
        villesCIanchorageVancouverWinnipegCambridgeBay.add(vancouver);
        villesCIanchorageVancouverWinnipegCambridgeBay.add(winnipeg);
        villesCIanchorageVancouverWinnipegCambridgeBay.add(cambridgeBay);
        CarteItineraire anchorageVancouverWinnipegCambridgeBay = new CarteItineraire(18, 12, 24, villesCIanchorageVancouverWinnipegCambridgeBay);

        ArrayList<Ville> villesCDathinaManila = new ArrayList<Ville>();
        villesCDathinaManila.add(athina);
        villesCDathinaManila.add(manila);
        CarteDestination athinaManila = new CarteDestination(14, villesCDathinaManila);


        ArrayList<Ville> villesCDbangkokTokyo = new ArrayList<Ville>();
        villesCDbangkokTokyo.add(bangkok);
        villesCDbangkokTokyo.add(tokyo);
        CarteDestination bangkokTokyo = new CarteDestination(6, villesCDbangkokTokyo);

        ArrayList<Ville> villesCDbuenosAiresManila = new ArrayList<Ville>();
        villesCDbuenosAiresManila.add(buenosAires);
        villesCDbuenosAiresManila.add(manila);
        CarteDestination buenosAiresManila = new CarteDestination(17, villesCDbuenosAiresManila);

        ArrayList<Ville> villesCDbuenosAiresMarseille = new ArrayList<Ville>();
        villesCDbuenosAiresMarseille.add(buenosAires);
        villesCDbuenosAiresMarseille.add(marseille);
        CarteDestination buenosAiresMarseille = new CarteDestination(18, villesCDbuenosAiresMarseille);

        ArrayList<Ville> villesCDbuenosAiresSydney = new ArrayList<Ville>();
        villesCDbuenosAiresSydney.add(buenosAires);
        villesCDbuenosAiresSydney.add(sydney);
        CarteDestination buenosAiresSydney = new CarteDestination(13, villesCDbuenosAiresSydney);

        ArrayList<Ville> villesCDcapTownJakarta = new ArrayList<Ville>();
        villesCDcapTownJakarta.add(capTown);
        villesCDcapTownJakarta.add(jakarta);
        CarteDestination capTownJakarta = new CarteDestination(13, villesCDcapTownJakarta);

        ArrayList<Ville> villesCDcaracasAlZahira = new ArrayList<Ville>();
        villesCDcaracasAlZahira.add(caracas);
        villesCDcaracasAlZahira.add(alQahira);
        CarteDestination caracasAlZahira = new CarteDestination(13, villesCDcaracasAlZahira);

        ArrayList<Ville> villesCDcaracasAthina = new ArrayList<Ville>();
        villesCDcaracasAthina.add(caracas);
        villesCDcaracasAthina.add(athina);
        CarteDestination caracasAthina = new CarteDestination(13, villesCDcaracasAthina);

        ArrayList<Ville> villesCIcasablancaAlQahiraTehran = new ArrayList<Ville>();
        villesCIcasablancaAlQahiraTehran.add(casablanca);
        villesCIcasablancaAlQahiraTehran.add(alQahira);
        villesCIcasablancaAlQahiraTehran.add(tehran);
        CarteItineraire casablancaAlQahiraTehran = new CarteItineraire(9, 6, 15, villesCIcasablancaAlQahiraTehran);

        ArrayList<Ville> villesCDcasablancaHonolulu = new ArrayList<Ville>();
        villesCDcasablancaHonolulu.add(casablanca);
        villesCDcasablancaHonolulu.add(honolulu);
        CarteDestination casablancaHonolulu = new CarteDestination(16, villesCDcasablancaHonolulu);

        ArrayList<Ville> villesCDcasablancaYakutsk = new ArrayList<Ville>();
        villesCDcasablancaYakutsk.add(casablanca);
        villesCDcasablancaYakutsk.add(yakutsk);
        CarteDestination casablancaYakutsk = new CarteDestination(16, villesCDcasablancaYakutsk);

        ArrayList<Ville> villesCDdarEsSalaamTokyo = new ArrayList<Ville>();
        villesCDdarEsSalaamTokyo.add(darEsSalaam);
        villesCDdarEsSalaamTokyo.add(tokyo);
        CarteDestination darEsSalaamTokyo = new CarteDestination(15, villesCDdarEsSalaamTokyo);

        ArrayList<Ville> villesCDdjiboutiLahore = new ArrayList<Ville>();
        villesCDdjiboutiLahore.add(djibouti);
        villesCDdjiboutiLahore.add(lahore);
        CarteDestination djiboutiLahore = new CarteDestination(7, villesCDdjiboutiLahore);

        ArrayList<Ville> villesCDedinburgLuanda = new ArrayList<Ville>();
        villesCDedinburgLuanda.add(edinburg);
        villesCDedinburgLuanda.add(luanda);
        CarteDestination edinburgLuanda = new CarteDestination(10, villesCDedinburgLuanda);

        ArrayList<Ville> villesCDedinburgHongKong = new ArrayList<Ville>();
        villesCDedinburgHongKong.add(edinburg);
        villesCDedinburgHongKong.add(hongKong);
        CarteDestination edinburgHongKong = new CarteDestination(17, villesCDedinburgHongKong);

        ArrayList<Ville> villesCDedinburgSydney = new ArrayList<Ville>();
        villesCDedinburgSydney.add(edinburg);
        villesCDedinburgSydney.add(sydney);
        CarteDestination edinburgSydney = new CarteDestination(25, villesCDedinburgSydney);

        ArrayList<Ville> villesCDedinburgTokyo = new ArrayList<Ville>();
        villesCDedinburgTokyo.add(edinburg);
        villesCDedinburgTokyo.add(tokyo);
        CarteDestination edinburgTokyo = new CarteDestination(22, villesCDedinburgTokyo);

        ArrayList<Ville> villesCDhamburgBeijing = new ArrayList<Ville>();
        villesCDhamburgBeijing.add(hamburg);
        villesCDhamburgBeijing.add(beijing);
        CarteDestination hamburgBeijing = new CarteDestination(13, villesCDhamburgBeijing);

        ArrayList<Ville> villesCDhamburgDarEsSalaam = new ArrayList<Ville>();
        villesCDhamburgDarEsSalaam.add(hamburg);
        villesCDhamburgDarEsSalaam.add(darEsSalaam);
        CarteDestination hamburgDarEsSalaam = new CarteDestination(8, villesCDhamburgDarEsSalaam);

        ArrayList<Ville> villesCDhongKongJakarta = new ArrayList<Ville>();
        villesCDhongKongJakarta.add(hongKong);
        villesCDhongKongJakarta.add(jakarta);
        CarteDestination hongKongJakarta = new CarteDestination(5, villesCDhongKongJakarta);

        ArrayList<Ville> villesCDjakartaSydney = new ArrayList<Ville>();
        villesCDjakartaSydney.add(jakarta);
        villesCDjakartaSydney.add(sydney);
        CarteDestination jakartaSydney = new CarteDestination(7, villesCDjakartaSydney);

        ArrayList<Ville> villesCDLagosHongKong = new ArrayList<Ville>();
        villesCDLagosHongKong.add(lagos);
        villesCDLagosHongKong.add(hongKong);
        CarteDestination lagosHongKong = new CarteDestination(14, villesCDLagosHongKong);

        ArrayList<Ville> villesCIlagosLuandaDarEsSalaamDjibouti = new ArrayList<Ville>();
        villesCIlagosLuandaDarEsSalaamDjibouti.add(lagos);
        villesCIlagosLuandaDarEsSalaamDjibouti.add(luanda);
        villesCIlagosLuandaDarEsSalaamDjibouti.add(darEsSalaam);
        villesCIlagosLuandaDarEsSalaamDjibouti.add(djibouti);
        CarteItineraire lagosLuandaDarEsSalaamDjibouti = new CarteItineraire(9, 6, 15, villesCIlagosLuandaDarEsSalaamDjibouti);

        ArrayList<Ville> villesCDlagostehran = new ArrayList<Ville>();
        villesCDlagostehran.add(lagos);
        villesCDlagostehran.add(tehran);
        CarteDestination lagostehran = new CarteDestination(10, villesCDlagostehran);

        ArrayList<Ville> villesCDlimaJakarta = new ArrayList<Ville>();
        villesCDlimaJakarta.add(lima);
        villesCDlimaJakarta.add(jakarta);
        CarteDestination limaJakarta = new CarteDestination(14, villesCDlimaJakarta);

        ArrayList<Ville> villesCDlosAngelesDarEsSalaam = new ArrayList<Ville>();
        villesCDlosAngelesDarEsSalaam.add(losAngeles);
        villesCDlosAngelesDarEsSalaam.add(darEsSalaam);
        CarteDestination losAngelesDarEsSalaam = new CarteDestination(17, villesCDlosAngelesDarEsSalaam);

        ArrayList<Ville> villesCDlosAngelesHamburg = new ArrayList<Ville>();
        villesCDlosAngelesHamburg.add(losAngeles);
        villesCDlosAngelesHamburg.add(hamburg);
        CarteDestination losAngelesHamburg = new CarteDestination(14, villesCDlosAngelesHamburg);

        ArrayList<Ville> villesCDlosAngelesJakarta = new ArrayList<Ville>();
        villesCDlosAngelesJakarta.add(losAngeles);
        villesCDlosAngelesJakarta.add(jakarta);
        CarteDestination losAngelesJakarta = new CarteDestination(11, villesCDlosAngelesJakarta);

        ArrayList<Ville> villesCDlosAngelesRioDeJaneiro = new ArrayList<Ville>();
        villesCDlosAngelesRioDeJaneiro.add(losAngeles);
        villesCDlosAngelesRioDeJaneiro.add(rioDeJaneiro);
        CarteDestination losAngelesRioDeJaneiro = new CarteDestination(15, villesCDlosAngelesRioDeJaneiro);

        ArrayList<Ville> villesCImanilaHonoluluPortMeresby = new ArrayList<Ville>();
        villesCImanilaHonoluluPortMeresby.add(manila);
        villesCImanilaHonoluluPortMeresby.add(honolulu);
        villesCImanilaHonoluluPortMeresby.add(portMeresby);
        CarteItineraire manilaHonoluluPortMeresby = new CarteItineraire(13, 9, 19, villesCImanilaHonoluluPortMeresby);

        ArrayList<Ville> villesCDmarseilleAlZahira = new ArrayList<Ville>();
        villesCDmarseilleAlZahira.add(marseille);
        villesCDmarseilleAlZahira.add(alQahira);
        CarteDestination marseilleAlZahira = new CarteDestination(5, villesCDmarseilleAlZahira);

        ArrayList<Ville> villesCDmarseilleBeijing = new ArrayList<Ville>();
        villesCDmarseilleBeijing.add(marseille);
        villesCDmarseilleBeijing.add(beijing);
        CarteDestination marseilleBeijing = new CarteDestination(14, villesCDmarseilleBeijing);

        ArrayList<Ville> villesCDmarseilleChristchurch = new ArrayList<Ville>();
        villesCDmarseilleChristchurch.add(marseille);
        villesCDmarseilleChristchurch.add(christchurch);
        CarteDestination marseillechristchurch = new CarteDestination(23, villesCDmarseilleChristchurch);

        ArrayList<Ville> villesCDmarseilleJakarta = new ArrayList<Ville>();
        villesCDmarseilleJakarta.add(marseille);
        villesCDmarseilleJakarta.add(jakarta);
        CarteDestination marseilleJakarta = new CarteDestination(18, villesCDmarseilleJakarta);

        ArrayList<Ville> villesCDmexicoBeijing = new ArrayList<Ville>();
        villesCDmexicoBeijing.add(mexico);
        villesCDmexicoBeijing.add(beijing);
        CarteDestination mexicoBeijing = new CarteDestination(13, villesCDmexicoBeijing);

        ArrayList<Ville> villesCImexicoCaracasLimaValparaiso = new ArrayList<Ville>();
        villesCImexicoCaracasLimaValparaiso.add(mexico);
        villesCImexicoCaracasLimaValparaiso.add(caracas);
        villesCImexicoCaracasLimaValparaiso.add(lima);
        villesCImexicoCaracasLimaValparaiso.add(valparaiso);
        CarteItineraire mexicoCaracasLimaValparaiso = new CarteItineraire(15, 10, 21, villesCImexicoCaracasLimaValparaiso);

        ArrayList<Ville> villesCDmexicoMumbai = new ArrayList<Ville>();
        villesCDmexicoMumbai.add(mexico);
        villesCDmexicoMumbai.add(mumbai);
        CarteDestination mexicoMumbai = new CarteDestination(15, villesCDmexicoMumbai);

        ArrayList<Ville> villesCDmexicoNewYork = new ArrayList<Ville>();
        villesCDmexicoNewYork.add(mexico);
        villesCDmexicoNewYork.add(newYork);
        CarteDestination mexicoNewYork = new CarteDestination(11, villesCDmexicoNewYork);

        ArrayList<Ville> villesCDmiamiBuenosAires = new ArrayList<Ville>();
        villesCDmiamiBuenosAires.add(miami);
        villesCDmiamiBuenosAires.add(buenosAires);
        CarteDestination miamiBuenosAires = new CarteDestination(9, villesCDmiamiBuenosAires);

        ArrayList<Ville> villesCDmiamiMoskva = new ArrayList<Ville>();
        villesCDmiamiMoskva.add(miami);
        villesCDmiamiMoskva.add(moskva);
        CarteDestination miamiMoskva = new CarteDestination(13, villesCDmiamiMoskva);

        ArrayList<Ville> villesCDmoskvaHongKong = new ArrayList<Ville>();
        villesCDmoskvaHongKong.add(moskva);
        villesCDmoskvaHongKong.add(hongKong);
        CarteDestination moskvaHongKong = new CarteDestination(13, villesCDmoskvaHongKong);

        ArrayList<Ville> villesCDmoskvaPetropavlovsk = new ArrayList<Ville>();
        villesCDmoskvaPetropavlovsk.add(moskva);
        villesCDmoskvaPetropavlovsk.add(petropavlosk);
        CarteDestination moskvaPetropavlovsk = new CarteDestination(15, villesCDmoskvaPetropavlovsk);

        ArrayList<Ville> villesCDmoskvaToamasina = new ArrayList<Ville>();
        villesCDmoskvaToamasina.add(moskva);
        villesCDmoskvaToamasina.add(toamasina);
        CarteDestination moskvaToamasina = new CarteDestination(11, villesCDmoskvaToamasina);

        ArrayList<Ville> villesCDmumbaiBeijing = new ArrayList<Ville>();
        villesCDmumbaiBeijing.add(mumbai);
        villesCDmumbaiBeijing.add(beijing);
        CarteDestination mumbaiBeijing = new CarteDestination(6, villesCDmumbaiBeijing);

        ArrayList<Ville> villesCImurmanskTiksiNovosibirskYakutskPetropavlovsk = new ArrayList<Ville>();
        villesCImurmanskTiksiNovosibirskYakutskPetropavlovsk.add(murmansk);
        villesCImurmanskTiksiNovosibirskYakutskPetropavlovsk.add(tiksi);
        villesCImurmanskTiksiNovosibirskYakutskPetropavlovsk.add(novosibirsk);
        villesCImurmanskTiksiNovosibirskYakutskPetropavlovsk.add(yakutsk);
        villesCImurmanskTiksiNovosibirskYakutskPetropavlovsk.add(petropavlosk);
        CarteItineraire murmanskTiksiNovosibirskYakutskPetropavlovsk = new CarteItineraire(30, 20, 36, villesCImurmanskTiksiNovosibirskYakutskPetropavlovsk);

        ArrayList<Ville> villesCDnewYorkCapTown = new ArrayList<Ville>();
        villesCDnewYorkCapTown.add(newYork);
        villesCDnewYorkCapTown.add(capTown);
        CarteDestination newYorkCapTown = new CarteDestination(19, villesCDnewYorkCapTown);

        ArrayList<Ville> villesCDnewYorkMarseille = new ArrayList<Ville>();
        villesCDnewYorkMarseille.add(newYork);
        villesCDnewYorkMarseille.add(marseille);
        CarteDestination newYorkMarseille = new CarteDestination(10, villesCDnewYorkMarseille);

        ArrayList<Ville> villesCDnewYorkMumbai = new ArrayList<Ville>();
        villesCDnewYorkMumbai.add(newYork);
        villesCDnewYorkMumbai.add(mumbai);
        CarteDestination newYorkMumbai = new CarteDestination(19, villesCDnewYorkMumbai);

        ArrayList<Ville> villesCDnewYorkSydney = new ArrayList<Ville>();
        villesCDnewYorkSydney.add(newYork);
        villesCDnewYorkSydney.add(sydney);
        CarteDestination newYorkSydney = new CarteDestination(17, villesCDnewYorkSydney);

        ArrayList<Ville> villesCDnewYorkTokyo = new ArrayList<Ville>();
        villesCDnewYorkTokyo.add(newYork);
        villesCDnewYorkTokyo.add(tokyo);
        CarteDestination newYorkTokyo = new CarteDestination(15, villesCDnewYorkTokyo);

        ArrayList<Ville> villesCDnovosibirskDarwin = new ArrayList<Ville>();
        villesCDnovosibirskDarwin.add(novosibirsk);
        villesCDnovosibirskDarwin.add(darwin);
        CarteDestination novosibirskDarwin = new CarteDestination(13, villesCDnovosibirskDarwin);

        ArrayList<Ville> villesCDreykjavikMumbai = new ArrayList<Ville>();
        villesCDreykjavikMumbai.add(reykjavik);
        villesCDreykjavikMumbai.add(mumbai);
        CarteDestination reykjavikMumbai = new CarteDestination(13, villesCDreykjavikMumbai);

        ArrayList<Ville> villesCDrioDeJaneiroDarEsSalaam = new ArrayList<Ville>();
        villesCDrioDeJaneiroDarEsSalaam.add(rioDeJaneiro);
        villesCDrioDeJaneiroDarEsSalaam.add(darEsSalaam);
        CarteDestination rioDeJaneiroDarEsSalaam = new CarteDestination(11, villesCDrioDeJaneiroDarEsSalaam);

        ArrayList<Ville> villesCDrioDeJaneiroHamburg = new ArrayList<Ville>();
        villesCDrioDeJaneiroHamburg.add(rioDeJaneiro);
        villesCDrioDeJaneiroHamburg.add(hamburg);
        CarteDestination rioDeJaneiroHamburg = new CarteDestination(18, villesCDrioDeJaneiroHamburg);

        ArrayList<Ville> villesCDrioDeJaneiroPerth = new ArrayList<Ville>();
        villesCDrioDeJaneiroPerth.add(rioDeJaneiro);
        villesCDrioDeJaneiroPerth.add(perth);
        CarteDestination rioDeJaneiroPerth = new CarteDestination(17, villesCDrioDeJaneiroPerth);

        ArrayList<Ville> villesCDrioDeJaneiroTokyo = new ArrayList<Ville>();
        villesCDrioDeJaneiroTokyo.add(rioDeJaneiro);
        villesCDrioDeJaneiroTokyo.add(tokyo);
        CarteDestination rioDeJaneiroTokyo = new CarteDestination(20, villesCDrioDeJaneiroTokyo);

        ArrayList<Ville> villesCItehranLahoreMumbaiBangkok = new ArrayList<Ville>();
        villesCItehranLahoreMumbaiBangkok.add(tehran);
        villesCItehranLahoreMumbaiBangkok.add(lahore);
        villesCItehranLahoreMumbaiBangkok.add(mumbai);
        villesCItehranLahoreMumbaiBangkok.add(bangkok);
        CarteItineraire tehranLahoreMumbaiBangkok = new CarteItineraire(13, 9, 19, villesCItehranLahoreMumbaiBangkok);

        ArrayList<Ville> villesCDtokyoSydney = new ArrayList<Ville>();
        villesCDtokyoSydney.add(tokyo);
        villesCDtokyoSydney.add(sydney);
        CarteDestination tokyoSydney = new CarteDestination(11, villesCDtokyoSydney);

        ArrayList<Ville> villesCDvalparaisoRioDeJaneiro = new ArrayList<Ville>();
        villesCDvalparaisoRioDeJaneiro.add(valparaiso);
        villesCDvalparaisoRioDeJaneiro.add(rioDeJaneiro);
        CarteDestination valparaisoRioDeJaneiro = new CarteDestination(6, villesCDvalparaisoRioDeJaneiro);

        ArrayList<Ville> villesCDvancouverEdinburg = new ArrayList<Ville>();
        villesCDvancouverEdinburg.add(vancouver);
        villesCDvancouverEdinburg.add(edinburg);
        CarteDestination vancouverEdinburg = new CarteDestination(13, villesCDvancouverEdinburg);

        ArrayList<Ville> villesCDvancouverMiami = new ArrayList<Ville>();
        villesCDvancouverMiami.add(vancouver);
        villesCDvancouverMiami.add(miami);
        CarteDestination vancouverMiami = new CarteDestination(9, villesCDvancouverMiami);

        ArrayList<Ville> villesCDwinnipegPerth = new ArrayList<Ville>();
        villesCDwinnipegPerth.add(winnipeg);
        villesCDwinnipegPerth.add(perth);
        CarteDestination winnipegPerth = new CarteDestination(14, villesCDwinnipegPerth);

        ArrayList<CarteDestination> ensemblesDestinationsItineraires = new ArrayList<CarteDestination>();
        ensemblesDestinationsItineraires.add(alZahiraSydney);
        ensemblesDestinationsItineraires.add(anchorageCambridgeRaykjavikMurmanskTiksi);
        ensemblesDestinationsItineraires.add(anchorageVancouverWinnipegCambridgeBay);
        ensemblesDestinationsItineraires.add(athinaManila);
        ensemblesDestinationsItineraires.add(bangkokTokyo);
        ensemblesDestinationsItineraires.add(buenosAiresManila);
        ensemblesDestinationsItineraires.add(buenosAiresMarseille);
        ensemblesDestinationsItineraires.add(buenosAiresSydney);
        ensemblesDestinationsItineraires.add(capTownJakarta);
        ensemblesDestinationsItineraires.add(caracasAlZahira);
        ensemblesDestinationsItineraires.add(caracasAthina);
        ensemblesDestinationsItineraires.add(casablancaAlQahiraTehran);
        ensemblesDestinationsItineraires.add(casablancaHonolulu);
        ensemblesDestinationsItineraires.add(casablancaYakutsk);
        ensemblesDestinationsItineraires.add(darEsSalaamTokyo);
        ensemblesDestinationsItineraires.add(djiboutiLahore);
        ensemblesDestinationsItineraires.add(edinburgHongKong);
        ensemblesDestinationsItineraires.add(edinburgSydney);
        ensemblesDestinationsItineraires.add(edinburgTokyo);
        ensemblesDestinationsItineraires.add(hamburgBeijing);
        ensemblesDestinationsItineraires.add(hamburgDarEsSalaam);
        ensemblesDestinationsItineraires.add(hongKongJakarta);
        ensemblesDestinationsItineraires.add(jakartaSydney);
        ensemblesDestinationsItineraires.add(lagosHongKong);
        ensemblesDestinationsItineraires.add(lagosLuandaDarEsSalaamDjibouti);
        ensemblesDestinationsItineraires.add(lagostehran);
        ensemblesDestinationsItineraires.add(limaJakarta);
        ensemblesDestinationsItineraires.add(losAngelesDarEsSalaam);
        ensemblesDestinationsItineraires.add(losAngelesHamburg);
        ensemblesDestinationsItineraires.add(losAngelesJakarta);
        ensemblesDestinationsItineraires.add(losAngelesRioDeJaneiro);
        ensemblesDestinationsItineraires.add(manilaHonoluluPortMeresby);
        ensemblesDestinationsItineraires.add(marseilleAlZahira);
        ensemblesDestinationsItineraires.add(marseilleBeijing);
        ensemblesDestinationsItineraires.add(marseillechristchurch);
        ensemblesDestinationsItineraires.add(marseilleJakarta);
        ensemblesDestinationsItineraires.add(mexicoBeijing);
        ensemblesDestinationsItineraires.add(mexicoCaracasLimaValparaiso);
        ensemblesDestinationsItineraires.add(mexicoMumbai);
        ensemblesDestinationsItineraires.add(mexicoNewYork);
        ensemblesDestinationsItineraires.add(miamiBuenosAires);
        ensemblesDestinationsItineraires.add(miamiMoskva);
        ensemblesDestinationsItineraires.add(moskvaHongKong);
        ensemblesDestinationsItineraires.add(moskvaPetropavlovsk);
        ensemblesDestinationsItineraires.add(moskvaToamasina);
        ensemblesDestinationsItineraires.add(mumbaiBeijing);
        ensemblesDestinationsItineraires.add(murmanskTiksiNovosibirskYakutskPetropavlovsk);
        ensemblesDestinationsItineraires.add(newYorkCapTown);
        ensemblesDestinationsItineraires.add(newYorkMarseille);
        ensemblesDestinationsItineraires.add(newYorkMumbai);
        ensemblesDestinationsItineraires.add(newYorkSydney);
        ensemblesDestinationsItineraires.add(newYorkTokyo);
        ensemblesDestinationsItineraires.add(novosibirskDarwin);
        ensemblesDestinationsItineraires.add(reykjavikMumbai);
        ensemblesDestinationsItineraires.add(rioDeJaneiroDarEsSalaam);
        ensemblesDestinationsItineraires.add(rioDeJaneiroHamburg);
        ensemblesDestinationsItineraires.add(rioDeJaneiroPerth);
        ensemblesDestinationsItineraires.add(rioDeJaneiroTokyo);
        ensemblesDestinationsItineraires.add(tehranLahoreMumbaiBangkok);
        ensemblesDestinationsItineraires.add(tokyoSydney);
        ensemblesDestinationsItineraires.add(valparaisoRioDeJaneiro);
        ensemblesDestinationsItineraires.add(vancouverEdinburg);
        ensemblesDestinationsItineraires.add(vancouverMiami);
        ensemblesDestinationsItineraires.add(winnipegPerth);

        return ensemblesDestinationsItineraires;
    }
}
