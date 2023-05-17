package be.kdg.finalproject.database;
import be.kdg.finalproject.domain.platform.Municipality;
import be.kdg.finalproject.domain.platform.PostCode;
import be.kdg.finalproject.repository.municipality.MunicipalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class MunicipalitySeeder { //For some weird reason ghent is not there?
	private final MunicipalityRepository municipalityRepository;

	@Autowired
	public MunicipalitySeeder(MunicipalityRepository municipalityRepository) {
		this.municipalityRepository = municipalityRepository;
	}


	public void seed() {
		Municipality antwerp = new Municipality("Antwerp", 51.218322900000004, 4.403472825833973);
		Set<PostCode> antwerpPostalCodes = new HashSet<>();
		PostCode postalCode2000 = new PostCode(2000);
		antwerpPostalCodes.add(postalCode2000);
		PostCode postalCode2018 = new PostCode(2018);
		antwerpPostalCodes.add(postalCode2018);
		PostCode postalCode2020 = new PostCode(2020);
		antwerpPostalCodes.add(postalCode2020);
		PostCode postalCode2030 = new PostCode(2030);
		antwerpPostalCodes.add(postalCode2030);
		PostCode postalCode2040 = new PostCode(2040);
		antwerpPostalCodes.add(postalCode2040);
		PostCode postalCode2050 = new PostCode(2050);
		antwerpPostalCodes.add(postalCode2050);
		PostCode postalCode2060 = new PostCode(2060);
		antwerpPostalCodes.add(postalCode2060);
		PostCode postalCode2100 = new PostCode(2100);
		antwerpPostalCodes.add(postalCode2100);
		PostCode postalCode2140 = new PostCode(2140);
		antwerpPostalCodes.add(postalCode2140);
		PostCode postalCode2170 = new PostCode(2170);
		antwerpPostalCodes.add(postalCode2170);
		PostCode postalCode2180 = new PostCode(2180);
		antwerpPostalCodes.add(postalCode2180);
		PostCode postalCode2600 = new PostCode(2600);
		antwerpPostalCodes.add(postalCode2600);
		PostCode postalCode2610 = new PostCode(2610);
		antwerpPostalCodes.add(postalCode2610);
		PostCode postalCode2660 = new PostCode(2660);
		antwerpPostalCodes.add(postalCode2660);
		antwerp.setPostcodes(antwerpPostalCodes);
		municipalityRepository.save(antwerp);

		Municipality zwijndrecht = new Municipality("Zwijndrecht", 51.21101638727273, 4.326921361818182);
		Set<PostCode> zwijndrechtPostalCodes = new HashSet<>();
		PostCode postalCode2070 = new PostCode(2070);
		zwijndrechtPostalCodes.add(postalCode2070);
		zwijndrecht.setPostcodes(zwijndrechtPostalCodes);
		municipalityRepository.save(zwijndrecht);

		Municipality wijnegem = new Municipality("Wijnegem", 51.23052853983051, 4.505171768644067);
		Set<PostCode> wijnegemPostalCodes = new HashSet<>();
		PostCode postalCode2110 = new PostCode(2110);
		wijnegemPostalCodes.add(postalCode2110);
		wijnegem.setPostcodes(wijnegemPostalCodes);
		municipalityRepository.save(wijnegem);

		Municipality borsbeek = new Municipality("Borsbeek", 51.19406795384616, 4.483057494871796);
		Set<PostCode> borsbeekPostalCodes = new HashSet<>();
		PostCode postalCode2150 = new PostCode(2150);
		borsbeekPostalCodes.add(postalCode2150);
		borsbeek.setPostcodes(borsbeekPostalCodes);
		municipalityRepository.save(borsbeek);

		Municipality wommelgem = new Municipality("Wommelgem", 51.2050801037037, 4.512773835185185);
		Set<PostCode> wommelgemPostalCodes = new HashSet<>();
		PostCode postalCode2160 = new PostCode(2160);
		wommelgemPostalCodes.add(postalCode2160);
		wommelgem.setPostcodes(wommelgemPostalCodes);
		municipalityRepository.save(wommelgem);

		Municipality herentals = new Municipality("Herentals", 51.163118272659936, 4.832027840471381);
		Set<PostCode> herentalsPostalCodes = new HashSet<>();
		PostCode postalCode2200 = new PostCode(2200);
		herentalsPostalCodes.add(postalCode2200);
		herentals.setPostcodes(herentalsPostalCodes);
		municipalityRepository.save(herentals);

		Municipality heist_op_den_berg = new Municipality("Heist-op-den-Berg", 51.06978496050809, 4.7206547838337185);
		Set<PostCode> heist_op_den_bergPostalCodes = new HashSet<>();
		PostCode postalCode2220 = new PostCode(2220);
		heist_op_den_bergPostalCodes.add(postalCode2220);
		PostCode postalCode2221 = new PostCode(2221);
		heist_op_den_bergPostalCodes.add(postalCode2221);
		PostCode postalCode2222 = new PostCode(2222);
		heist_op_den_bergPostalCodes.add(postalCode2222);
		PostCode postalCode2223 = new PostCode(2223);
		heist_op_den_bergPostalCodes.add(postalCode2223);
		heist_op_den_berg.setPostcodes(heist_op_den_bergPostalCodes);
		municipalityRepository.save(heist_op_den_berg);

		Municipality herselt = new Municipality("Herselt", 51.05161072096774, 4.885761603225807);
		Set<PostCode> herseltPostalCodes = new HashSet<>();
		PostCode postalCode2230 = new PostCode(2230);
		herseltPostalCodes.add(postalCode2230);
		herselt.setPostcodes(herseltPostalCodes);
		municipalityRepository.save(herselt);

		Municipality hulshout = new Municipality("Hulshout", 51.06650771754386, 4.805098235087719);
		Set<PostCode> hulshoutPostalCodes = new HashSet<>();
		PostCode postalCode2235 = new PostCode(2235);
		hulshoutPostalCodes.add(postalCode2235);
		hulshout.setPostcodes(hulshoutPostalCodes);
		municipalityRepository.save(hulshout);

		Municipality zandhoven = new Municipality("Zandhoven", 51.20006659722222, 4.6430626847222225);
		Set<PostCode> zandhovenPostalCodes = new HashSet<>();
		PostCode postalCode2240 = new PostCode(2240);
		zandhovenPostalCodes.add(postalCode2240);
		PostCode postalCode2242 = new PostCode(2242);
		zandhovenPostalCodes.add(postalCode2242);
		PostCode postalCode2243 = new PostCode(2243);
		zandhovenPostalCodes.add(postalCode2243);
		zandhoven.setPostcodes(zandhovenPostalCodes);
		municipalityRepository.save(zandhoven);

		Municipality olen = new Municipality("Olen", 51.155942903321034, 4.878933471033211);
		Set<PostCode> olenPostalCodes = new HashSet<>();
		PostCode postalCode2250 = new PostCode(2250);
		olenPostalCodes.add(postalCode2250);
		olen.setPostcodes(olenPostalCodes);
		municipalityRepository.save(olen);

		Municipality westerlo = new Municipality("Westerlo", 51.1105044962963, 4.897579929100529);
		Set<PostCode> westerloPostalCodes = new HashSet<>();
		PostCode postalCode2260 = new PostCode(2260);
		westerloPostalCodes.add(postalCode2260);
		westerlo.setPostcodes(westerloPostalCodes);
		municipalityRepository.save(westerlo);

		Municipality herenthout = new Municipality("Herenthout", 51.14396027777778, 4.766145779835391);
		Set<PostCode> herenthoutPostalCodes = new HashSet<>();
		PostCode postalCode2270 = new PostCode(2270);
		herenthoutPostalCodes.add(postalCode2270);
		herenthout.setPostcodes(herenthoutPostalCodes);
		municipalityRepository.save(herenthout);

		Municipality lille = new Municipality("Lille", 51.240943780633145, 4.822396659962756);
		Set<PostCode> lillePostalCodes = new HashSet<>();
		PostCode postalCode2275 = new PostCode(2275);
		lillePostalCodes.add(postalCode2275);
		lille.setPostcodes(lillePostalCodes);
		municipalityRepository.save(lille);

		Municipality grobbendonk = new Municipality("Grobbendonk", 51.18754244774775, 4.7429840459459465);
		Set<PostCode> grobbendonkPostalCodes = new HashSet<>();
		PostCode postalCode2280 = new PostCode(2280);
		grobbendonkPostalCodes.add(postalCode2280);
		PostCode postalCode2288 = new PostCode(2288);
		grobbendonkPostalCodes.add(postalCode2288);
		grobbendonk.setPostcodes(grobbendonkPostalCodes);
		municipalityRepository.save(grobbendonk);

		Municipality vorselaar = new Municipality("Vorselaar", 51.2089575490566, 4.783800788679245);
		Set<PostCode> vorselaarPostalCodes = new HashSet<>();
		PostCode postalCode2290 = new PostCode(2290);
		vorselaarPostalCodes.add(postalCode2290);
		vorselaar.setPostcodes(vorselaarPostalCodes);
		municipalityRepository.save(vorselaar);

		Municipality turnhout = new Municipality("Turnhout", 51.31717240963636, 4.936604818363636);
		Set<PostCode> turnhoutPostalCodes = new HashSet<>();
		PostCode postalCode2300 = new PostCode(2300);
		turnhoutPostalCodes.add(postalCode2300);
		turnhout.setPostcodes(turnhoutPostalCodes);
		municipalityRepository.save(turnhout);

		Municipality rijkevorsel = new Municipality("Rijkevorsel", 51.34639249120879, 4.762087413736264);
		Set<PostCode> rijkevorselPostalCodes = new HashSet<>();
		PostCode postalCode2310 = new PostCode(2310);
		rijkevorselPostalCodes.add(postalCode2310);
		rijkevorsel.setPostcodes(rijkevorselPostalCodes);
		municipalityRepository.save(rijkevorsel);

		Municipality hoogstraten = new Municipality("Hoogstraten", 51.399427940673576, 4.757513369170985);
		Set<PostCode> hoogstratenPostalCodes = new HashSet<>();
		PostCode postalCode2320 = new PostCode(2320);
		hoogstratenPostalCodes.add(postalCode2320);
		PostCode postalCode2321 = new PostCode(2321);
		hoogstratenPostalCodes.add(postalCode2321);
		PostCode postalCode2322 = new PostCode(2322);
		hoogstratenPostalCodes.add(postalCode2322);
		PostCode postalCode2323 = new PostCode(2323);
		hoogstratenPostalCodes.add(postalCode2323);
		PostCode postalCode2328 = new PostCode(2328);
		hoogstratenPostalCodes.add(postalCode2328);
		hoogstraten.setPostcodes(hoogstratenPostalCodes);
		municipalityRepository.save(hoogstraten);

		Municipality merksplas = new Municipality("Merksplas", 51.36028012826087, 4.858922232608696);
		Set<PostCode> merksplasPostalCodes = new HashSet<>();
		PostCode postalCode2330 = new PostCode(2330);
		merksplasPostalCodes.add(postalCode2330);
		merksplas.setPostcodes(merksplasPostalCodes);
		municipalityRepository.save(merksplas);

		Municipality beerse = new Municipality("Beerse", 51.318534366180934, 4.835548895081967);
		Set<PostCode> beersePostalCodes = new HashSet<>();
		PostCode postalCode2340 = new PostCode(2340);
		beersePostalCodes.add(postalCode2340);
		beerse.setPostcodes(beersePostalCodes);
		municipalityRepository.save(beerse);

		Municipality vosselaar = new Municipality("Vosselaar", 51.309617226117396, 4.88605226015078);
		Set<PostCode> vosselaarPostalCodes = new HashSet<>();
		PostCode postalCode2350 = new PostCode(2350);
		vosselaarPostalCodes.add(postalCode2350);
		vosselaar.setPostcodes(vosselaarPostalCodes);
		municipalityRepository.save(vosselaar);

		Municipality oud_turnhout = new Municipality("Oud-Turnhout", 51.31043232566372, 5.005956507964602);
		Set<PostCode> oud_turnhoutPostalCodes = new HashSet<>();
		PostCode postalCode2360 = new PostCode(2360);
		oud_turnhoutPostalCodes.add(postalCode2360);
		oud_turnhout.setPostcodes(oud_turnhoutPostalCodes);
		municipalityRepository.save(oud_turnhout);

		Municipality arendonk = new Municipality("Arendonk", 51.31106575045872, 5.092537894036697);
		Set<PostCode> arendonkPostalCodes = new HashSet<>();
		PostCode postalCode2370 = new PostCode(2370);
		arendonkPostalCodes.add(postalCode2370);
		arendonk.setPostcodes(arendonkPostalCodes);
		municipalityRepository.save(arendonk);

		Municipality ravels = new Municipality("Ravels", 51.3734613, 4.99657474375);
		Set<PostCode> ravelsPostalCodes = new HashSet<>();
		PostCode postalCode2380 = new PostCode(2380);
		ravelsPostalCodes.add(postalCode2380);
		PostCode postalCode2381 = new PostCode(2381);
		ravelsPostalCodes.add(postalCode2381);
		PostCode postalCode2382 = new PostCode(2382);
		ravelsPostalCodes.add(postalCode2382);
		ravels.setPostcodes(ravelsPostalCodes);
		municipalityRepository.save(ravels);

		Municipality baerle_duc = new Municipality("Baerle-Duc", 51.4383589, 4.9316995);
		Set<PostCode> baerle_ducPostalCodes = new HashSet<>();
		PostCode postalCode2387 = new PostCode(2387);
		baerle_ducPostalCodes.add(postalCode2387);
		baerle_duc.setPostcodes(baerle_ducPostalCodes);
		municipalityRepository.save(baerle_duc);

		Municipality malle = new Municipality("Malle", 51.296467814553985, 4.701864081220657);
		Set<PostCode> mallePostalCodes = new HashSet<>();
		PostCode postalCode2390 = new PostCode(2390);
		mallePostalCodes.add(postalCode2390);
		malle.setPostcodes(mallePostalCodes);
		municipalityRepository.save(malle);

		Municipality mol = new Municipality("Mol", 51.19507578772494, 5.125866809761496);
		Set<PostCode> molPostalCodes = new HashSet<>();
		PostCode postalCode2400 = new PostCode(2400);
		molPostalCodes.add(postalCode2400);
		mol.setPostcodes(molPostalCodes);
		municipalityRepository.save(mol);

		Municipality laakdal = new Municipality("Laakdal", 51.090265501123596, 5.036910901123595);
		Set<PostCode> laakdalPostalCodes = new HashSet<>();
		PostCode postalCode2430 = new PostCode(2430);
		laakdalPostalCodes.add(postalCode2430);
		PostCode postalCode2431 = new PostCode(2431);
		laakdalPostalCodes.add(postalCode2431);
		laakdal.setPostcodes(laakdalPostalCodes);
		municipalityRepository.save(laakdal);

		Municipality geel = new Municipality("Geel", 51.16780410300051, 4.990587755496637);
		Set<PostCode> geelPostalCodes = new HashSet<>();
		PostCode postalCode2440 = new PostCode(2440);
		geelPostalCodes.add(postalCode2440);
		geel.setPostcodes(geelPostalCodes);
		municipalityRepository.save(geel);

		Municipality meerhout = new Municipality("Meerhout", 51.126220705333985, 5.077979085882065);
		Set<PostCode> meerhoutPostalCodes = new HashSet<>();
		PostCode postalCode2450 = new PostCode(2450);
		meerhoutPostalCodes.add(postalCode2450);
		meerhout.setPostcodes(meerhoutPostalCodes);
		municipalityRepository.save(meerhout);

		Municipality kasterlee = new Municipality("Kasterlee", 51.23082061889764, 4.928999698425197);
		Set<PostCode> kasterleePostalCodes = new HashSet<>();
		PostCode postalCode2460 = new PostCode(2460);
		kasterleePostalCodes.add(postalCode2460);
		kasterlee.setPostcodes(kasterleePostalCodes);
		municipalityRepository.save(kasterlee);

		Municipality retie = new Municipality("Retie", 51.26949523785402, 5.084599240524983);
		Set<PostCode> retiePostalCodes = new HashSet<>();
		PostCode postalCode2470 = new PostCode(2470);
		retiePostalCodes.add(postalCode2470);
		retie.setPostcodes(retiePostalCodes);
		municipalityRepository.save(retie);

		Municipality dessel = new Municipality("Dessel", 51.241582492394144, 5.121843209017251);
		Set<PostCode> desselPostalCodes = new HashSet<>();
		PostCode postalCode2480 = new PostCode(2480);
		desselPostalCodes.add(postalCode2480);
		dessel.setPostcodes(desselPostalCodes);
		municipalityRepository.save(dessel);

		Municipality balen = new Municipality("Balen", 51.16966720056759, 5.173418153188947);
		Set<PostCode> balenPostalCodes = new HashSet<>();
		PostCode postalCode2490 = new PostCode(2490);
		balenPostalCodes.add(postalCode2490);
		PostCode postalCode2491 = new PostCode(2491);
		balenPostalCodes.add(postalCode2491);
		balen.setPostcodes(balenPostalCodes);
		municipalityRepository.save(balen);

		Municipality lierre = new Municipality("Lierre", 51.13380424429757, 4.563824228035659);
		Set<PostCode> lierrePostalCodes = new HashSet<>();
		PostCode postalCode2500 = new PostCode(2500);
		lierrePostalCodes.add(postalCode2500);
		lierre.setPostcodes(lierrePostalCodes);
		municipalityRepository.save(lierre);

		Municipality ranst = new Municipality("Ranst", 51.17976840028818, 4.59639256721902);
		Set<PostCode> ranstPostalCodes = new HashSet<>();
		PostCode postalCode2520 = new PostCode(2520);
		ranstPostalCodes.add(postalCode2520);
		ranst.setPostcodes(ranstPostalCodes);
		municipalityRepository.save(ranst);

		Municipality boechout = new Municipality("Boechout", 51.16278078214286, 4.495687883035714);
		Set<PostCode> boechoutPostalCodes = new HashSet<>();
		PostCode postalCode2530 = new PostCode(2530);
		boechoutPostalCodes.add(postalCode2530);
		PostCode postalCode2531 = new PostCode(2531);
		boechoutPostalCodes.add(postalCode2531);
		boechout.setPostcodes(boechoutPostalCodes);
		municipalityRepository.save(boechout);

		Municipality hove = new Municipality("Hove", 51.14523739881832, 4.4793877725258495);
		Set<PostCode> hovePostalCodes = new HashSet<>();
		PostCode postalCode2540 = new PostCode(2540);
		hovePostalCodes.add(postalCode2540);
		hove.setPostcodes(hovePostalCodes);
		municipalityRepository.save(hove);

		Municipality lint = new Municipality("Lint", 51.128454245535714, 4.499830384821428);
		Set<PostCode> lintPostalCodes = new HashSet<>();
		PostCode postalCode2547 = new PostCode(2547);
		lintPostalCodes.add(postalCode2547);
		lint.setPostcodes(lintPostalCodes);
		municipalityRepository.save(lint);

		Municipality kontich = new Municipality("Kontich", 51.13109862324324, 4.447361313783784);
		Set<PostCode> kontichPostalCodes = new HashSet<>();
		PostCode postalCode2550 = new PostCode(2550);
		kontichPostalCodes.add(postalCode2550);
		kontich.setPostcodes(kontichPostalCodes);
		municipalityRepository.save(kontich);

		Municipality nijlen = new Municipality("Nijlen", 51.15778231339713, 4.654707976076555);
		Set<PostCode> nijlenPostalCodes = new HashSet<>();
		PostCode postalCode2560 = new PostCode(2560);
		nijlenPostalCodes.add(postalCode2560);
		nijlen.setPostcodes(nijlenPostalCodes);
		municipalityRepository.save(nijlen);

		Municipality duffel = new Municipality("Duffel", 51.097152264963505, 4.511000386861314);
		Set<PostCode> duffelPostalCodes = new HashSet<>();
		PostCode postalCode2570 = new PostCode(2570);
		duffelPostalCodes.add(postalCode2570);
		duffel.setPostcodes(duffelPostalCodes);
		municipalityRepository.save(duffel);

		Municipality putte = new Municipality("Putte", 51.05715936133333, 4.64371515);
		Set<PostCode> puttePostalCodes = new HashSet<>();
		PostCode postalCode2580 = new PostCode(2580);
		puttePostalCodes.add(postalCode2580);
		putte.setPostcodes(puttePostalCodes);
		municipalityRepository.save(putte);

		Municipality berlaar = new Municipality("Berlaar", 51.10660408780842, 4.654242418916303);
		Set<PostCode> berlaarPostalCodes = new HashSet<>();
		PostCode postalCode2590 = new PostCode(2590);
		berlaarPostalCodes.add(postalCode2590);
		berlaar.setPostcodes(berlaarPostalCodes);
		municipalityRepository.save(berlaar);

		Municipality hemiksem = new Municipality("Hemiksem", 51.148198810948905, 4.345558919708029);
		Set<PostCode> hemiksemPostalCodes = new HashSet<>();
		PostCode postalCode2620 = new PostCode(2620);
		hemiksemPostalCodes.add(postalCode2620);
		hemiksem.setPostcodes(hemiksemPostalCodes);
		municipalityRepository.save(hemiksem);

		Municipality schelle = new Municipality("Schelle", 51.12227027709251, 4.346200184140969);
		Set<PostCode> schellePostalCodes = new HashSet<>();
		PostCode postalCode2627 = new PostCode(2627);
		schellePostalCodes.add(postalCode2627);
		schelle.setPostcodes(schellePostalCodes);
		municipalityRepository.save(schelle);

		Municipality aartselaar = new Municipality("Aartselaar", 51.13171574355972, 4.38188012177986);
		Set<PostCode> aartselaarPostalCodes = new HashSet<>();
		PostCode postalCode2630 = new PostCode(2630);
		aartselaarPostalCodes.add(postalCode2630);
		aartselaar.setPostcodes(aartselaarPostalCodes);
		municipalityRepository.save(aartselaar);

		Municipality mortsel = new Municipality("Mortsel", 51.17206198303571, 4.4611616553571425);
		Set<PostCode> mortselPostalCodes = new HashSet<>();
		PostCode postalCode2640 = new PostCode(2640);
		mortselPostalCodes.add(postalCode2640);
		mortsel.setPostcodes(mortselPostalCodes);
		municipalityRepository.save(mortsel);

		Municipality edegem = new Municipality("Edegem", 51.155074574675325, 4.443396883766234);
		Set<PostCode> edegemPostalCodes = new HashSet<>();
		PostCode postalCode2650 = new PostCode(2650);
		edegemPostalCodes.add(postalCode2650);
		edegem.setPostcodes(edegemPostalCodes);
		municipalityRepository.save(edegem);

		Municipality malines = new Municipality("Malines", 51.03415449541919, 4.477543428867762);
		Set<PostCode> malinesPostalCodes = new HashSet<>();
		PostCode postalCode2800 = new PostCode(2800);
		malinesPostalCodes.add(postalCode2800);
		PostCode postalCode2801 = new PostCode(2801);
		malinesPostalCodes.add(postalCode2801);
		PostCode postalCode2811 = new PostCode(2811);
		malinesPostalCodes.add(postalCode2811);
		PostCode postalCode2812 = new PostCode(2812);
		malinesPostalCodes.add(postalCode2812);
		malines.setPostcodes(malinesPostalCodes);
		municipalityRepository.save(malines);

		Municipality bonheiden = new Municipality("Bonheiden", 51.02510909833334, 4.565179046666667);
		Set<PostCode> bonheidenPostalCodes = new HashSet<>();
		PostCode postalCode2820 = new PostCode(2820);
		bonheidenPostalCodes.add(postalCode2820);
		bonheiden.setPostcodes(bonheidenPostalCodes);
		municipalityRepository.save(bonheiden);

		Municipality willebroek = new Municipality("Willebroek", 51.0568678509434, 4.363619454716981);
		Set<PostCode> willebroekPostalCodes = new HashSet<>();
		PostCode postalCode2830 = new PostCode(2830);
		willebroekPostalCodes.add(postalCode2830);
		willebroek.setPostcodes(willebroekPostalCodes);
		municipalityRepository.save(willebroek);

		Municipality rumst = new Municipality("Rumst", 51.09679656980392, 4.404968961568628);
		Set<PostCode> rumstPostalCodes = new HashSet<>();
		PostCode postalCode2840 = new PostCode(2840);
		rumstPostalCodes.add(postalCode2840);
		rumst.setPostcodes(rumstPostalCodes);
		municipalityRepository.save(rumst);

		Municipality niel = new Municipality("Niel", 51.10948997300885, 4.339834550884956);
		Set<PostCode> nielPostalCodes = new HashSet<>();
		PostCode postalCode2845 = new PostCode(2845);
		nielPostalCodes.add(postalCode2845);
		niel.setPostcodes(nielPostalCodes);
		municipalityRepository.save(niel);

		Municipality boom = new Municipality("Boom", 51.08836114058823, 4.366211247647059);
		Set<PostCode> boomPostalCodes = new HashSet<>();
		PostCode postalCode2850 = new PostCode(2850);
		boomPostalCodes.add(postalCode2850);
		boom.setPostcodes(boomPostalCodes);
		municipalityRepository.save(boom);

		Municipality sint_katelijne_waver = new Municipality("Sint-Katelijne-Waver", 51.05214462104283, 4.501247409683426);
		Set<PostCode> sint_katelijne_waverPostalCodes = new HashSet<>();
		PostCode postalCode2860 = new PostCode(2860);
		sint_katelijne_waverPostalCodes.add(postalCode2860);
		PostCode postalCode2861 = new PostCode(2861);
		sint_katelijne_waverPostalCodes.add(postalCode2861);
		sint_katelijne_waver.setPostcodes(sint_katelijne_waverPostalCodes);
		municipalityRepository.save(sint_katelijne_waver);

		Municipality puurs_sint_amands = new Municipality("Puurs-Sint-Amands", 51.062399952678575, 4.292666100892857);
		Set<PostCode> puurs_sint_amandsPostalCodes = new HashSet<>();
		PostCode postalCode2870 = new PostCode(2870);
		puurs_sint_amandsPostalCodes.add(postalCode2870);
		PostCode postalCode2890 = new PostCode(2890);
		puurs_sint_amandsPostalCodes.add(postalCode2890);
		puurs_sint_amands.setPostcodes(puurs_sint_amandsPostalCodes);
		municipalityRepository.save(puurs_sint_amands);

		Municipality bornem = new Municipality("Bornem", 51.096661106281, 4.24184332198347);
		Set<PostCode> bornemPostalCodes = new HashSet<>();
		PostCode postalCode2880 = new PostCode(2880);
		bornemPostalCodes.add(postalCode2880);
		bornem.setPostcodes(bornemPostalCodes);
		municipalityRepository.save(bornem);

		Municipality schoten = new Municipality("Schoten", 51.25281062325581, 4.4887730174418605);
		Set<PostCode> schotenPostalCodes = new HashSet<>();
		PostCode postalCode2900 = new PostCode(2900);
		schotenPostalCodes.add(postalCode2900);
		schoten.setPostcodes(schotenPostalCodes);
		municipalityRepository.save(schoten);

		Municipality essen = new Municipality("Essen", 51.45127829719626, 4.452562111214953);
		Set<PostCode> essenPostalCodes = new HashSet<>();
		PostCode postalCode2910 = new PostCode(2910);
		essenPostalCodes.add(postalCode2910);
		essen.setPostcodes(essenPostalCodes);
		municipalityRepository.save(essen);

		Municipality kalmthout = new Municipality("Kalmthout", 51.38138172857143, 4.48232113277311);
		Set<PostCode> kalmthoutPostalCodes = new HashSet<>();
		PostCode postalCode2920 = new PostCode(2920);
		kalmthoutPostalCodes.add(postalCode2920);
		kalmthout.setPostcodes(kalmthoutPostalCodes);
		municipalityRepository.save(kalmthout);

		Municipality brasschaat = new Municipality("Brasschaat", 51.29531716698113, 4.489873223584906);
		Set<PostCode> brasschaatPostalCodes = new HashSet<>();
		PostCode postalCode2930 = new PostCode(2930);
		brasschaatPostalCodes.add(postalCode2930);
		brasschaat.setPostcodes(brasschaatPostalCodes);
		municipalityRepository.save(brasschaat);

		Municipality stabroek = new Municipality("Stabroek", 51.32952189326241, 4.374479212765958);
		Set<PostCode> stabroekPostalCodes = new HashSet<>();
		PostCode postalCode2940 = new PostCode(2940);
		stabroekPostalCodes.add(postalCode2940);
		stabroek.setPostcodes(stabroekPostalCodes);
		municipalityRepository.save(stabroek);

		Municipality kapellen = new Municipality("Kapellen", 51.32906995317919, 4.448988171098266);
		Set<PostCode> kapellenPostalCodes = new HashSet<>();
		PostCode postalCode2950 = new PostCode(2950);
		kapellenPostalCodes.add(postalCode2950);
		kapellen.setPostcodes(kapellenPostalCodes);
		municipalityRepository.save(kapellen);

		Municipality brecht = new Municipality("Brecht", 51.32490964939759, 4.625568656626506);
		Set<PostCode> brechtPostalCodes = new HashSet<>();
		PostCode postalCode2960 = new PostCode(2960);
		brechtPostalCodes.add(postalCode2960);
		brecht.setPostcodes(brechtPostalCodes);
		municipalityRepository.save(brecht);

		Municipality schilde = new Municipality("Schilde", 51.26588569848485, 4.568951792424242);
		Set<PostCode> schildePostalCodes = new HashSet<>();
		PostCode postalCode2970 = new PostCode(2970);
		schildePostalCodes.add(postalCode2970);
		schilde.setPostcodes(schildePostalCodes);
		municipalityRepository.save(schilde);

		Municipality zoersel = new Municipality("Zoersel", 51.25218643674699, 4.6657394210843375);
		Set<PostCode> zoerselPostalCodes = new HashSet<>();
		PostCode postalCode2980 = new PostCode(2980);
		zoerselPostalCodes.add(postalCode2980);
		zoersel.setPostcodes(zoerselPostalCodes);
		municipalityRepository.save(zoersel);

		Municipality wuustwezel = new Municipality("Wuustwezel", 51.38525, 4.565174345040151);
		Set<PostCode> wuustwezelPostalCodes = new HashSet<>();
		PostCode postalCode2990 = new PostCode(2990);
		wuustwezelPostalCodes.add(postalCode2990);
		wuustwezel.setPostcodes(wuustwezelPostalCodes);
		municipalityRepository.save(wuustwezel);

		Municipality gand = new Municipality("Gand", 51.05522278579391, 3.7106312807051554);
		Set<PostCode> gandPostalCodes = new HashSet<>();
		PostCode postalCode9000 = new PostCode(9000);
		gandPostalCodes.add(postalCode9000);
		PostCode postalCode9030 = new PostCode(9030);
		gandPostalCodes.add(postalCode9030);
		PostCode postalCode9031 = new PostCode(9031);
		gandPostalCodes.add(postalCode9031);
		PostCode postalCode9032 = new PostCode(9032);
		gandPostalCodes.add(postalCode9032);
		PostCode postalCode9040 = new PostCode(9040);
		gandPostalCodes.add(postalCode9040);
		PostCode postalCode9041 = new PostCode(9041);
		gandPostalCodes.add(postalCode9041);
		PostCode postalCode9042 = new PostCode(9042);
		gandPostalCodes.add(postalCode9042);
		PostCode postalCode9050 = new PostCode(9050);
		gandPostalCodes.add(postalCode9050);
		PostCode postalCode9051 = new PostCode(9051);
		gandPostalCodes.add(postalCode9051);
		PostCode postalCode9052 = new PostCode(9052);
		gandPostalCodes.add(postalCode9052);
		gand.setPostcodes(gandPostalCodes);
		municipalityRepository.save(gand);

		Municipality zelzate = new Municipality("Zelzate", 51.199278162952645, 3.819332685793872);
		Set<PostCode> zelzatePostalCodes = new HashSet<>();
		PostCode postalCode9060 = new PostCode(9060);
		zelzatePostalCodes.add(postalCode9060);
		zelzate.setPostcodes(zelzatePostalCodes);
		municipalityRepository.save(zelzate);

		Municipality destelbergen = new Municipality("Destelbergen", 51.056891116551725, 3.7993167779310344);
		Set<PostCode> destelbergenPostalCodes = new HashSet<>();
		PostCode postalCode9070 = new PostCode(9070);
		destelbergenPostalCodes.add(postalCode9070);
		destelbergen.setPostcodes(destelbergenPostalCodes);
		municipalityRepository.save(destelbergen);

		Municipality lochristi = new Municipality("Lochristi", 51.09122764615385, 3.8191882170329667);
		Set<PostCode> lochristiPostalCodes = new HashSet<>();
		PostCode postalCode9080 = new PostCode(9080);
		lochristiPostalCodes.add(postalCode9080);
		lochristi.setPostcodes(lochristiPostalCodes);
		municipalityRepository.save(lochristi);

		Municipality melle = new Municipality("Melle", 51.00840874909091, 3.7896512163636364);
		Set<PostCode> mellePostalCodes = new HashSet<>();
		PostCode postalCode9090 = new PostCode(9090);
		mellePostalCodes.add(postalCode9090);
		melle.setPostcodes(mellePostalCodes);
		municipalityRepository.save(melle);

		Municipality saint_nicolas = new Municipality("Saint-Nicolas", 51.16726946876972, 4.156839053785489);
		Set<PostCode> saint_nicolasPostalCodes = new HashSet<>();
		PostCode postalCode9100 = new PostCode(9100);
		saint_nicolasPostalCodes.add(postalCode9100);
		PostCode postalCode9111 = new PostCode(9111);
		saint_nicolasPostalCodes.add(postalCode9111);
		PostCode postalCode9112 = new PostCode(9112);
		saint_nicolasPostalCodes.add(postalCode9112);
		saint_nicolas.setPostcodes(saint_nicolasPostalCodes);
		municipalityRepository.save(saint_nicolas);

		Municipality beveren = new Municipality("Beveren", 51.21048241137931, 4.2512815465517235);
		Set<PostCode> beverenPostalCodes = new HashSet<>();
		PostCode postalCode9120 = new PostCode(9120);
		beverenPostalCodes.add(postalCode9120);
		PostCode postalCode9130 = new PostCode(9130);
		beverenPostalCodes.add(postalCode9130);
		beveren.setPostcodes(beverenPostalCodes);
		municipalityRepository.save(beveren);

		Municipality tamise = new Municipality("Tamise", 51.12639366528822, 4.204355529636592);
		Set<PostCode> tamisePostalCodes = new HashSet<>();
		PostCode postalCode9140 = new PostCode(9140);
		tamisePostalCodes.add(postalCode9140);
		tamise.setPostcodes(tamisePostalCodes);
		municipalityRepository.save(tamise);

		Municipality kruibeke = new Municipality("Kruibeke", 51.160106854545454, 4.3027216);
		Set<PostCode> kruibekePostalCodes = new HashSet<>();
		PostCode postalCode9150 = new PostCode(9150);
		kruibekePostalCodes.add(postalCode9150);
		kruibeke.setPostcodes(kruibekePostalCodes);
		municipalityRepository.save(kruibeke);

		Municipality lokeren = new Municipality("Lokeren", 51.11213456292135, 3.9875930764044942);
		Set<PostCode> lokerenPostalCodes = new HashSet<>();
		PostCode postalCode9160 = new PostCode(9160);
		lokerenPostalCodes.add(postalCode9160);
		lokeren.setPostcodes(lokerenPostalCodes);
		municipalityRepository.save(lokeren);

		Municipality sint_gillis_waas = new Municipality("Sint-Gillis-Waas", 51.21901592748815, 4.095119496208531);
		Set<PostCode> sint_gillis_waasPostalCodes = new HashSet<>();
		PostCode postalCode9170 = new PostCode(9170);
		sint_gillis_waasPostalCodes.add(postalCode9170);
		sint_gillis_waas.setPostcodes(sint_gillis_waasPostalCodes);
		municipalityRepository.save(sint_gillis_waas);

		Municipality moerbeke = new Municipality("Moerbeke", 51.18274596538461, 3.941267382692308);
		Set<PostCode> moerbekePostalCodes = new HashSet<>();
		PostCode postalCode9180 = new PostCode(9180);
		moerbekePostalCodes.add(postalCode9180);
		moerbeke.setPostcodes(moerbekePostalCodes);
		municipalityRepository.save(moerbeke);

		Municipality wachtebeke = new Municipality("Wachtebeke", 51.19980375959596, 3.8612500252525255);
		Set<PostCode> wachtebekePostalCodes = new HashSet<>();
		PostCode postalCode9185 = new PostCode(9185);
		wachtebekePostalCodes.add(postalCode9185);
		wachtebeke.setPostcodes(wachtebekePostalCodes);
		municipalityRepository.save(wachtebeke);

		Municipality stekene = new Municipality("Stekene", 51.211179019999996, 4.039932235);
		Set<PostCode> stekenePostalCodes = new HashSet<>();
		PostCode postalCode9190 = new PostCode(9190);
		stekenePostalCodes.add(postalCode9190);
		stekene.setPostcodes(stekenePostalCodes);
		municipalityRepository.save(stekene);

		Municipality termonde = new Municipality("Termonde", 51.0171449845679, 4.086542929423869);
		Set<PostCode> termondePostalCodes = new HashSet<>();
		PostCode postalCode9200 = new PostCode(9200);
		termondePostalCodes.add(postalCode9200);
		termonde.setPostcodes(termondePostalCodes);
		municipalityRepository.save(termonde);

		Municipality hamme = new Municipality("Hamme", 51.07660778252788, 4.166865804832714);
		Set<PostCode> hammePostalCodes = new HashSet<>();
		PostCode postalCode9220 = new PostCode(9220);
		hammePostalCodes.add(postalCode9220);
		hamme.setPostcodes(hammePostalCodes);
		municipalityRepository.save(hamme);

		Municipality wetteren = new Municipality("Wetteren", 50.996455255115094, 3.8689994685422);
		Set<PostCode> wetterenPostalCodes = new HashSet<>();
		PostCode postalCode9230 = new PostCode(9230);
		wetterenPostalCodes.add(postalCode9230);
		wetteren.setPostcodes(wetterenPostalCodes);
		municipalityRepository.save(wetteren);

		Municipality zele = new Municipality("Zele", 51.070787373010376, 4.034519334948097);
		Set<PostCode> zelePostalCodes = new HashSet<>();
		PostCode postalCode9240 = new PostCode(9240);
		zelePostalCodes.add(postalCode9240);
		zele.setPostcodes(zelePostalCodes);
		municipalityRepository.save(zele);

		Municipality waasmunster = new Municipality("Waasmunster", 51.133395768115946, 4.068327512318841);
		Set<PostCode> waasmunsterPostalCodes = new HashSet<>();
		PostCode postalCode9250 = new PostCode(9250);
		waasmunsterPostalCodes.add(postalCode9250);
		waasmunster.setPostcodes(waasmunsterPostalCodes);
		municipalityRepository.save(waasmunster);

		Municipality buggenhout = new Municipality("Buggenhout", 51.01269591320755, 4.197374167924528);
		Set<PostCode> buggenhoutPostalCodes = new HashSet<>();
		PostCode postalCode9255 = new PostCode(9255);
		buggenhoutPostalCodes.add(postalCode9255);
		buggenhout.setPostcodes(buggenhoutPostalCodes);
		municipalityRepository.save(buggenhout);

		Municipality wichelen = new Municipality("Wichelen", 50.99703295897436, 3.9540019384615386);
		Set<PostCode> wichelenPostalCodes = new HashSet<>();
		PostCode postalCode9260 = new PostCode(9260);
		wichelenPostalCodes.add(postalCode9260);
		wichelen.setPostcodes(wichelenPostalCodes);
		municipalityRepository.save(wichelen);

		Municipality laarne = new Municipality("Laarne", 51.03692131481481, 3.8793430518518517);
		Set<PostCode> laarnePostalCodes = new HashSet<>();
		PostCode postalCode9270 = new PostCode(9270);
		laarnePostalCodes.add(postalCode9270);
		laarne.setPostcodes(laarnePostalCodes);
		municipalityRepository.save(laarne);

		Municipality lebbeke = new Municipality("Lebbeke", 50.996982444940976, 4.13837593693086);
		Set<PostCode> lebbekePostalCodes = new HashSet<>();
		PostCode postalCode9280 = new PostCode(9280);
		lebbekePostalCodes.add(postalCode9280);
		lebbeke.setPostcodes(lebbekePostalCodes);
		municipalityRepository.save(lebbeke);

		Municipality berlare = new Municipality("Berlare", 51.02830660034722, 3.9961050083333336);
		Set<PostCode> berlarePostalCodes = new HashSet<>();
		PostCode postalCode9290 = new PostCode(9290);
		berlarePostalCodes.add(postalCode9290);
		berlare.setPostcodes(berlarePostalCodes);
		municipalityRepository.save(berlare);

		Municipality alost = new Municipality("Alost", 50.94469603447005, 4.041694823686636);
		Set<PostCode> alostPostalCodes = new HashSet<>();
		PostCode postalCode9300 = new PostCode(9300);
		alostPostalCodes.add(postalCode9300);
		PostCode postalCode9308 = new PostCode(9308);
		alostPostalCodes.add(postalCode9308);
		PostCode postalCode9310 = new PostCode(9310);
		alostPostalCodes.add(postalCode9310);
		PostCode postalCode9320 = new PostCode(9320);
		alostPostalCodes.add(postalCode9320);
		alost.setPostcodes(alostPostalCodes);
		municipalityRepository.save(alost);

		Municipality lede = new Municipality("Lede", 50.966100954700856, 3.945333557051282);
		Set<PostCode> ledePostalCodes = new HashSet<>();
		PostCode postalCode9340 = new PostCode(9340);
		ledePostalCodes.add(postalCode9340);
		lede.setPostcodes(ledePostalCodes);
		municipalityRepository.save(lede);

		Municipality ninove = new Municipality("Ninove", 50.81433917701864, 4.017063816770186);
		Set<PostCode> ninovePostalCodes = new HashSet<>();
		PostCode postalCode9400 = new PostCode(9400);
		ninovePostalCodes.add(postalCode9400);
		PostCode postalCode9401 = new PostCode(9401);
		ninovePostalCodes.add(postalCode9401);
		PostCode postalCode9402 = new PostCode(9402);
		ninovePostalCodes.add(postalCode9402);
		PostCode postalCode9403 = new PostCode(9403);
		ninovePostalCodes.add(postalCode9403);
		PostCode postalCode9404 = new PostCode(9404);
		ninovePostalCodes.add(postalCode9404);
		PostCode postalCode9406 = new PostCode(9406);
		ninovePostalCodes.add(postalCode9406);
		ninove.setPostcodes(ninovePostalCodes);
		municipalityRepository.save(ninove);

		Municipality erpe_mere = new Municipality("Erpe-Mere", 50.92306163020339, 3.9705106016949157);
		Set<PostCode> erpe_merePostalCodes = new HashSet<>();
		PostCode postalCode9420 = new PostCode(9420);
		erpe_merePostalCodes.add(postalCode9420);
		erpe_mere.setPostcodes(erpe_merePostalCodes);
		municipalityRepository.save(erpe_mere);

		Municipality haaltert = new Municipality("Haaltert", 50.891306216949154, 4.009658281355932);
		Set<PostCode> haaltertPostalCodes = new HashSet<>();
		PostCode postalCode9450 = new PostCode(9450);
		haaltertPostalCodes.add(postalCode9450);
		PostCode postalCode9451 = new PostCode(9451);
		haaltertPostalCodes.add(postalCode9451);
		haaltert.setPostcodes(haaltertPostalCodes);
		municipalityRepository.save(haaltert);

		Municipality denderleeuw = new Municipality("Denderleeuw", 50.88059380666667, 4.069266866666666);
		Set<PostCode> denderleeuwPostalCodes = new HashSet<>();
		PostCode postalCode9470 = new PostCode(9470);
		denderleeuwPostalCodes.add(postalCode9470);
		PostCode postalCode9472 = new PostCode(9472);
		denderleeuwPostalCodes.add(postalCode9472);
		PostCode postalCode9473 = new PostCode(9473);
		denderleeuwPostalCodes.add(postalCode9473);
		denderleeuw.setPostcodes(denderleeuwPostalCodes);
		municipalityRepository.save(denderleeuw);

		Municipality grammont = new Municipality("Grammont", 50.76905672520107, 3.876924446380697);
		Set<PostCode> grammontPostalCodes = new HashSet<>();
		PostCode postalCode9500 = new PostCode(9500);
		grammontPostalCodes.add(postalCode9500);
		PostCode postalCode9506 = new PostCode(9506);
		grammontPostalCodes.add(postalCode9506);
		grammont.setPostcodes(grammontPostalCodes);
		municipalityRepository.save(grammont);

		Municipality sint_lievens_houtem = new Municipality("Sint-Lievens-Houtem", 50.93054847435898, 3.873083887179487);
		Set<PostCode> sint_lievens_houtemPostalCodes = new HashSet<>();
		PostCode postalCode9520 = new PostCode(9520);
		sint_lievens_houtemPostalCodes.add(postalCode9520);
		PostCode postalCode9521 = new PostCode(9521);
		sint_lievens_houtemPostalCodes.add(postalCode9521);
		sint_lievens_houtem.setPostcodes(sint_lievens_houtemPostalCodes);
		municipalityRepository.save(sint_lievens_houtem);

		Municipality herzele = new Municipality("Herzele", 50.87876923103448, 3.8827542620689655);
		Set<PostCode> herzelePostalCodes = new HashSet<>();
		PostCode postalCode9550 = new PostCode(9550);
		herzelePostalCodes.add(postalCode9550);
		PostCode postalCode9551 = new PostCode(9551);
		herzelePostalCodes.add(postalCode9551);
		PostCode postalCode9552 = new PostCode(9552);
		herzelePostalCodes.add(postalCode9552);
		herzele.setPostcodes(herzelePostalCodes);
		municipalityRepository.save(herzele);

		Municipality lierde = new Municipality("Lierde", 50.80589144736842, 3.8366515210526315);
		Set<PostCode> lierdePostalCodes = new HashSet<>();
		PostCode postalCode9570 = new PostCode(9570);
		lierdePostalCodes.add(postalCode9570);
		PostCode postalCode9571 = new PostCode(9571);
		lierdePostalCodes.add(postalCode9571);
		PostCode postalCode9572 = new PostCode(9572);
		lierdePostalCodes.add(postalCode9572);
		lierde.setPostcodes(lierdePostalCodes);
		municipalityRepository.save(lierde);

		Municipality renaix = new Municipality("Renaix", 50.75003861904025, 3.6183631790763675);
		Set<PostCode> renaixPostalCodes = new HashSet<>();
		PostCode postalCode9600 = new PostCode(9600);
		renaixPostalCodes.add(postalCode9600);
		renaix.setPostcodes(renaixPostalCodes);
		municipalityRepository.save(renaix);

		Municipality zottegem = new Municipality("Zottegem", 50.869277058441554, 3.8076825757575756);
		Set<PostCode> zottegemPostalCodes = new HashSet<>();
		PostCode postalCode9620 = new PostCode(9620);
		zottegemPostalCodes.add(postalCode9620);
		zottegem.setPostcodes(zottegemPostalCodes);
		municipalityRepository.save(zottegem);

		Municipality zwalin = new Municipality("Zwalin", 50.87471615571429, 3.7325111485714286);
		Set<PostCode> zwalinPostalCodes = new HashSet<>();
		PostCode postalCode9630 = new PostCode(9630);
		zwalinPostalCodes.add(postalCode9630);
		PostCode postalCode9636 = new PostCode(9636);
		zwalinPostalCodes.add(postalCode9636);
		zwalin.setPostcodes(zwalinPostalCodes);
		municipalityRepository.save(zwalin);

		Municipality brakel = new Municipality("Brakel", 50.786285025287356, 3.766970851724138);
		Set<PostCode> brakelPostalCodes = new HashSet<>();
		PostCode postalCode9660 = new PostCode(9660);
		brakelPostalCodes.add(postalCode9660);
		PostCode postalCode9661 = new PostCode(9661);
		brakelPostalCodes.add(postalCode9661);
		brakel.setPostcodes(brakelPostalCodes);
		municipalityRepository.save(brakel);

		Municipality horebeke = new Municipality("Horebeke", 50.82623256, 3.69463625);
		Set<PostCode> horebekePostalCodes = new HashSet<>();
		PostCode postalCode9667 = new PostCode(9667);
		horebekePostalCodes.add(postalCode9667);
		horebeke.setPostcodes(horebekePostalCodes);
		municipalityRepository.save(horebeke);

		Municipality maarkedal = new Municipality("Maarkedal", 50.787686074468084, 3.6381084680851066);
		Set<PostCode> maarkedalPostalCodes = new HashSet<>();
		PostCode postalCode9680 = new PostCode(9680);
		maarkedalPostalCodes.add(postalCode9680);
		PostCode postalCode9681 = new PostCode(9681);
		maarkedalPostalCodes.add(postalCode9681);
		PostCode postalCode9688 = new PostCode(9688);
		maarkedalPostalCodes.add(postalCode9688);
		maarkedal.setPostcodes(maarkedalPostalCodes);
		municipalityRepository.save(maarkedal);

		Municipality kluisbergen = new Municipality("Kluisbergen", 50.7782986, 3.5212498);
		Set<PostCode> kluisbergenPostalCodes = new HashSet<>();
		PostCode postalCode9690 = new PostCode(9690);
		kluisbergenPostalCodes.add(postalCode9690);
		kluisbergen.setPostcodes(kluisbergenPostalCodes);
		municipalityRepository.save(kluisbergen);

		Municipality audenarde = new Municipality("Audenarde", 50.8479165410105, 3.6069350938976377);
		Set<PostCode> audenardePostalCodes = new HashSet<>();
		PostCode postalCode9700 = new PostCode(9700);
		audenardePostalCodes.add(postalCode9700);
		audenarde.setPostcodes(audenardePostalCodes);
		municipalityRepository.save(audenarde);

		Municipality kruisem = new Municipality("Kruisem", 50.904916709574465, 3.625563074468085);
		Set<PostCode> kruisemPostalCodes = new HashSet<>();
		PostCode postalCode9750 = new PostCode(9750);
		kruisemPostalCodes.add(postalCode9750);
		PostCode postalCode9770 = new PostCode(9770);
		kruisemPostalCodes.add(postalCode9770);
		PostCode postalCode9771 = new PostCode(9771);
		kruisemPostalCodes.add(postalCode9771);
		PostCode postalCode9772 = new PostCode(9772);
		kruisemPostalCodes.add(postalCode9772);
		kruisem.setPostcodes(kruisemPostalCodes);
		municipalityRepository.save(kruisem);

		Municipality wortegem_petegem = new Municipality("Wortegem-Petegem", 50.8485315, 3.5375547);
		Set<PostCode> wortegem_petegemPostalCodes = new HashSet<>();
		PostCode postalCode9790 = new PostCode(9790);
		wortegem_petegemPostalCodes.add(postalCode9790);
		wortegem_petegem.setPostcodes(wortegem_petegemPostalCodes);
		municipalityRepository.save(wortegem_petegem);

		Municipality deinze = new Municipality("Deinze", 50.980423145028404, 3.536356489630682);
		Set<PostCode> deinzePostalCodes = new HashSet<>();
		PostCode postalCode9800 = new PostCode(9800);
		deinzePostalCodes.add(postalCode9800);
		PostCode postalCode9850 = new PostCode(9850);
		deinzePostalCodes.add(postalCode9850);
		deinze.setPostcodes(deinzePostalCodes);
		municipalityRepository.save(deinze);

		Municipality nazareth = new Municipality("Nazareth", 50.96038663486683, 3.6028118491525425);
		Set<PostCode> nazarethPostalCodes = new HashSet<>();
		PostCode postalCode9810 = new PostCode(9810);
		nazarethPostalCodes.add(postalCode9810);
		nazareth.setPostcodes(nazarethPostalCodes);
		municipalityRepository.save(nazareth);

		Municipality merelbeke = new Municipality("Merelbeke", 51.004691522222224, 3.746090294444445);
		Set<PostCode> merelbekePostalCodes = new HashSet<>();
		PostCode postalCode9820 = new PostCode(9820);
		merelbekePostalCodes.add(postalCode9820);
		merelbeke.setPostcodes(merelbekePostalCodes);
		municipalityRepository.save(merelbeke);

		Municipality sint_martens_latem = new Municipality("Sint-Martens-Latem", 51.00827450181818, 3.6346381527272724);
		Set<PostCode> sint_martens_latemPostalCodes = new HashSet<>();
		PostCode postalCode9830 = new PostCode(9830);
		sint_martens_latemPostalCodes.add(postalCode9830);
		PostCode postalCode9831 = new PostCode(9831);
		sint_martens_latemPostalCodes.add(postalCode9831);
		sint_martens_latem.setPostcodes(sint_martens_latemPostalCodes);
		municipalityRepository.save(sint_martens_latem);

		Municipality de_pinte = new Municipality("De Pinte", 50.9924205, 3.6495958);
		Set<PostCode> de_pintePostalCodes = new HashSet<>();
		PostCode postalCode9840 = new PostCode(9840);
		de_pintePostalCodes.add(postalCode9840);
		de_pinte.setPostcodes(de_pintePostalCodes);
		municipalityRepository.save(de_pinte);

		Municipality oosterzele = new Municipality("Oosterzele", 50.941530889887645, 3.79343365);
		Set<PostCode> oosterzelePostalCodes = new HashSet<>();
		PostCode postalCode9860 = new PostCode(9860);
		oosterzelePostalCodes.add(postalCode9860);
		oosterzele.setPostcodes(oosterzelePostalCodes);
		municipalityRepository.save(oosterzele);

		Municipality zulte = new Municipality("Zulte", 50.93250421097561, 3.4578200219512194);
		Set<PostCode> zultePostalCodes = new HashSet<>();
		PostCode postalCode9870 = new PostCode(9870);
		zultePostalCodes.add(postalCode9870);
		zulte.setPostcodes(zultePostalCodes);
		municipalityRepository.save(zulte);

		Municipality aalter = new Municipality("Aalter", 51.081139587797615, 3.4440049401785715);
		Set<PostCode> aalterPostalCodes = new HashSet<>();
		PostCode postalCode9880 = new PostCode(9880);
		aalterPostalCodes.add(postalCode9880);
		PostCode postalCode9881 = new PostCode(9881);
		aalterPostalCodes.add(postalCode9881);
		PostCode postalCode9910 = new PostCode(9910);
		aalterPostalCodes.add(postalCode9910);
		aalter.setPostcodes(aalterPostalCodes);
		municipalityRepository.save(aalter);

		Municipality gavere = new Municipality("Gavere", 50.92845664754098, 3.663788132786885);
		Set<PostCode> gaverePostalCodes = new HashSet<>();
		PostCode postalCode9890 = new PostCode(9890);
		gaverePostalCodes.add(postalCode9890);
		gavere.setPostcodes(gaverePostalCodes);
		municipalityRepository.save(gavere);

		Municipality eeklo = new Municipality("Eeklo", 51.18854949116466, 3.5441298638554217);
		Set<PostCode> eekloPostalCodes = new HashSet<>();
		PostCode postalCode9900 = new PostCode(9900);
		eekloPostalCodes.add(postalCode9900);
		eeklo.setPostcodes(eekloPostalCodes);
		municipalityRepository.save(eeklo);

		Municipality lievegem = new Municipality("Lievegem", 51.11037955294118, 3.631542911764706);
		Set<PostCode> lievegemPostalCodes = new HashSet<>();
		PostCode postalCode9920 = new PostCode(9920);
		lievegemPostalCodes.add(postalCode9920);
		PostCode postalCode9921 = new PostCode(9921);
		lievegemPostalCodes.add(postalCode9921);
		PostCode postalCode9930 = new PostCode(9930);
		lievegemPostalCodes.add(postalCode9930);
		PostCode postalCode9931 = new PostCode(9931);
		lievegemPostalCodes.add(postalCode9931);
		PostCode postalCode9932 = new PostCode(9932);
		lievegemPostalCodes.add(postalCode9932);
		PostCode postalCode9950 = new PostCode(9950);
		lievegemPostalCodes.add(postalCode9950);
		lievegem.setPostcodes(lievegemPostalCodes);
		municipalityRepository.save(lievegem);

		Municipality evergem = new Municipality("Evergem", 51.11840824902724, 3.720140596108949);
		Set<PostCode> evergemPostalCodes = new HashSet<>();
		PostCode postalCode9940 = new PostCode(9940);
		evergemPostalCodes.add(postalCode9940);
		evergem.setPostcodes(evergemPostalCodes);
		municipalityRepository.save(evergem);

		Municipality assenede = new Municipality("Assenede", 51.22042196666667, 3.7634459);
		Set<PostCode> assenedePostalCodes = new HashSet<>();
		PostCode postalCode9960 = new PostCode(9960);
		assenedePostalCodes.add(postalCode9960);
		PostCode postalCode9961 = new PostCode(9961);
		assenedePostalCodes.add(postalCode9961);
		PostCode postalCode9968 = new PostCode(9968);
		assenedePostalCodes.add(postalCode9968);
		assenede.setPostcodes(assenedePostalCodes);
		municipalityRepository.save(assenede);

		Municipality kaprijke = new Municipality("Kaprijke", 51.22185601428571, 3.6171009428571423);
		Set<PostCode> kaprijkePostalCodes = new HashSet<>();
		PostCode postalCode9970 = new PostCode(9970);
		kaprijkePostalCodes.add(postalCode9970);
		PostCode postalCode9971 = new PostCode(9971);
		kaprijkePostalCodes.add(postalCode9971);
		kaprijke.setPostcodes(kaprijkePostalCodes);
		municipalityRepository.save(kaprijke);

		Municipality sint_laureins = new Municipality("Sint-Laureins", 51.238891745454545, 3.5214018727272727);
		Set<PostCode> sint_laureinsPostalCodes = new HashSet<>();
		PostCode postalCode9980 = new PostCode(9980);
		sint_laureinsPostalCodes.add(postalCode9980);
		PostCode postalCode9981 = new PostCode(9981);
		sint_laureinsPostalCodes.add(postalCode9981);
		PostCode postalCode9982 = new PostCode(9982);
		sint_laureinsPostalCodes.add(postalCode9982);
		PostCode postalCode9988 = new PostCode(9988);
		sint_laureinsPostalCodes.add(postalCode9988);
		sint_laureins.setPostcodes(sint_laureinsPostalCodes);
		municipalityRepository.save(sint_laureins);

		Municipality maldegem = new Municipality("Maldegem", 51.199868180575535, 3.4464941496402877);
		Set<PostCode> maldegemPostalCodes = new HashSet<>();
		PostCode postalCode9990 = new PostCode(9990);
		maldegemPostalCodes.add(postalCode9990);
		PostCode postalCode9991 = new PostCode(9991);
		maldegemPostalCodes.add(postalCode9991);
		PostCode postalCode9992 = new PostCode(9992);
		maldegemPostalCodes.add(postalCode9992);
		maldegem.setPostcodes(maldegemPostalCodes);
		municipalityRepository.save(maldegem);

		Municipality hal = new Municipality("Hal", 50.73555371076923, 4.239173496153846);
		Set<PostCode> halPostalCodes = new HashSet<>();
		PostCode postalCode1500 = new PostCode(1500);
		halPostalCodes.add(postalCode1500);
		PostCode postalCode1501 = new PostCode(1501);
		halPostalCodes.add(postalCode1501);
		PostCode postalCode1502 = new PostCode(1502);
		halPostalCodes.add(postalCode1502);
		hal.setPostcodes(halPostalCodes);
		municipalityRepository.save(hal);

		Municipality herne = new Municipality("Herne", 50.72974055897436, 4.036822685897436);
		Set<PostCode> hernePostalCodes = new HashSet<>();
		PostCode postalCode1540 = new PostCode(1540);
		hernePostalCodes.add(postalCode1540);
		PostCode postalCode1541 = new PostCode(1541);
		hernePostalCodes.add(postalCode1541);
		herne.setPostcodes(hernePostalCodes);
		municipalityRepository.save(herne);

		Municipality biévène = new Municipality("Biévène", 50.71501803333333, 3.9364914);
		Set<PostCode> biévènePostalCodes = new HashSet<>();
		PostCode postalCode1547 = new PostCode(1547);
		biévènePostalCodes.add(postalCode1547);
		biévène.setPostcodes(biévènePostalCodes);
		municipalityRepository.save(biévène);

		Municipality hoeilaart = new Municipality("Hoeilaart", 50.76590457169765, 4.476071095836431);
		Set<PostCode> hoeilaartPostalCodes = new HashSet<>();
		PostCode postalCode1560 = new PostCode(1560);
		hoeilaartPostalCodes.add(postalCode1560);
		hoeilaart.setPostcodes(hoeilaartPostalCodes);
		municipalityRepository.save(hoeilaart);

		Municipality gammerages = new Municipality("Gammerages", 50.750371063636365, 3.9998193636363633);
		Set<PostCode> gammeragesPostalCodes = new HashSet<>();
		PostCode postalCode1570 = new PostCode(1570);
		gammeragesPostalCodes.add(postalCode1570);
		gammerages.setPostcodes(gammeragesPostalCodes);
		municipalityRepository.save(gammerages);

		Municipality sint_pieters_leeuw = new Municipality("Sint-Pieters-Leeuw", 50.79048683851351, 4.273202677027028);
		Set<PostCode> sint_pieters_leeuwPostalCodes = new HashSet<>();
		PostCode postalCode1600 = new PostCode(1600);
		sint_pieters_leeuwPostalCodes.add(postalCode1600);
		PostCode postalCode1601 = new PostCode(1601);
		sint_pieters_leeuwPostalCodes.add(postalCode1601);
		PostCode postalCode1602 = new PostCode(1602);
		sint_pieters_leeuwPostalCodes.add(postalCode1602);
		sint_pieters_leeuw.setPostcodes(sint_pieters_leeuwPostalCodes);
		municipalityRepository.save(sint_pieters_leeuw);

		Municipality drogenbos = new Municipality("Drogenbos", 50.786532, 4.3173542);
		Set<PostCode> drogenbosPostalCodes = new HashSet<>();
		PostCode postalCode1620 = new PostCode(1620);
		drogenbosPostalCodes.add(postalCode1620);
		drogenbos.setPostcodes(drogenbosPostalCodes);
		municipalityRepository.save(drogenbos);

		Municipality linkebeek = new Municipality("Linkebeek", 50.7694422981982, 4.343863320720721);
		Set<PostCode> linkebeekPostalCodes = new HashSet<>();
		PostCode postalCode1630 = new PostCode(1630);
		linkebeekPostalCodes.add(postalCode1630);
		linkebeek.setPostcodes(linkebeekPostalCodes);
		municipalityRepository.save(linkebeek);

		Municipality rhode_saint_genèse = new Municipality("Rhode-Saint-Genèse", 50.74214966450382, 4.390438629389313);
		Set<PostCode> rhode_saint_genèsePostalCodes = new HashSet<>();
		PostCode postalCode1640 = new PostCode(1640);
		rhode_saint_genèsePostalCodes.add(postalCode1640);
		rhode_saint_genèse.setPostcodes(rhode_saint_genèsePostalCodes);
		municipalityRepository.save(rhode_saint_genèse);

		Municipality beersel = new Municipality("Beersel", 50.76613296666667, 4.308433379166666);
		Set<PostCode> beerselPostalCodes = new HashSet<>();
		PostCode postalCode1650 = new PostCode(1650);
		beerselPostalCodes.add(postalCode1650);
		PostCode postalCode1651 = new PostCode(1651);
		beerselPostalCodes.add(postalCode1651);
		PostCode postalCode1652 = new PostCode(1652);
		beerselPostalCodes.add(postalCode1652);
		PostCode postalCode1653 = new PostCode(1653);
		beerselPostalCodes.add(postalCode1653);
		PostCode postalCode1654 = new PostCode(1654);
		beerselPostalCodes.add(postalCode1654);
		beersel.setPostcodes(beerselPostalCodes);
		municipalityRepository.save(beersel);

		Municipality pepingen = new Municipality("Pepingen", 50.7495305, 4.1745294);
		Set<PostCode> pepingenPostalCodes = new HashSet<>();
		PostCode postalCode1670 = new PostCode(1670);
		pepingenPostalCodes.add(postalCode1670);
		PostCode postalCode1671 = new PostCode(1671);
		pepingenPostalCodes.add(postalCode1671);
		PostCode postalCode1673 = new PostCode(1673);
		pepingenPostalCodes.add(postalCode1673);
		PostCode postalCode1674 = new PostCode(1674);
		pepingenPostalCodes.add(postalCode1674);
		pepingen.setPostcodes(pepingenPostalCodes);
		municipalityRepository.save(pepingen);

		Municipality dilbeek = new Municipality("Dilbeek", 50.86675940498085, 4.245756473563218);
		Set<PostCode> dilbeekPostalCodes = new HashSet<>();
		PostCode postalCode1700 = new PostCode(1700);
		dilbeekPostalCodes.add(postalCode1700);
		PostCode postalCode1701 = new PostCode(1701);
		dilbeekPostalCodes.add(postalCode1701);
		PostCode postalCode1702 = new PostCode(1702);
		dilbeekPostalCodes.add(postalCode1702);
		PostCode postalCode1703 = new PostCode(1703);
		dilbeekPostalCodes.add(postalCode1703);
		dilbeek.setPostcodes(dilbeekPostalCodes);
		municipalityRepository.save(dilbeek);

		Municipality asse = new Municipality("Asse", 50.89284515, 4.251230571780244);
		Set<PostCode> assePostalCodes = new HashSet<>();
		PostCode postalCode1730 = new PostCode(1730);
		assePostalCodes.add(postalCode1730);
		PostCode postalCode1731 = new PostCode(1731);
		assePostalCodes.add(postalCode1731);
		asse.setPostcodes(assePostalCodes);
		municipalityRepository.save(asse);

		Municipality ternat = new Municipality("Ternat", 50.873934329456496, 4.160805666987251);
		Set<PostCode> ternatPostalCodes = new HashSet<>();
		PostCode postalCode1740 = new PostCode(1740);
		ternatPostalCodes.add(postalCode1740);
		PostCode postalCode1741 = new PostCode(1741);
		ternatPostalCodes.add(postalCode1741);
		PostCode postalCode1742 = new PostCode(1742);
		ternatPostalCodes.add(postalCode1742);
		ternat.setPostcodes(ternatPostalCodes);
		municipalityRepository.save(ternat);

		Municipality opwijk = new Municipality("Opwijk", 50.96544921313869, 4.194080125547446);
		Set<PostCode> opwijkPostalCodes = new HashSet<>();
		PostCode postalCode1745 = new PostCode(1745);
		opwijkPostalCodes.add(postalCode1745);
		opwijk.setPostcodes(opwijkPostalCodes);
		municipalityRepository.save(opwijk);

		Municipality lennik = new Municipality("Lennik", 50.801006396153845, 4.1551032346153844);
		Set<PostCode> lennikPostalCodes = new HashSet<>();
		PostCode postalCode1750 = new PostCode(1750);
		lennikPostalCodes.add(postalCode1750);
		lennik.setPostcodes(lennikPostalCodes);
		municipalityRepository.save(lennik);

		Municipality gooik = new Municipality("Gooik", 50.77376404456522, 4.066864179658386);
		Set<PostCode> gooikPostalCodes = new HashSet<>();
		PostCode postalCode1755 = new PostCode(1755);
		gooikPostalCodes.add(postalCode1755);
		gooik.setPostcodes(gooikPostalCodes);
		municipalityRepository.save(gooik);

		Municipality roosdaal = new Municipality("Roosdaal", 50.83615665, 4.098254714285714);
		Set<PostCode> roosdaalPostalCodes = new HashSet<>();
		PostCode postalCode1760 = new PostCode(1760);
		roosdaalPostalCodes.add(postalCode1760);
		PostCode postalCode1761 = new PostCode(1761);
		roosdaalPostalCodes.add(postalCode1761);
		roosdaal.setPostcodes(roosdaalPostalCodes);
		municipalityRepository.save(roosdaal);

		Municipality liedekerke = new Municipality("Liedekerke", 50.87427025294118, 4.0956235588235295);
		Set<PostCode> liedekerkePostalCodes = new HashSet<>();
		PostCode postalCode1770 = new PostCode(1770);
		liedekerkePostalCodes.add(postalCode1770);
		liedekerke.setPostcodes(liedekerkePostalCodes);
		municipalityRepository.save(liedekerke);

		Municipality wemmel = new Municipality("Wemmel", 50.90833940544747, 4.304220525291829);
		Set<PostCode> wemmelPostalCodes = new HashSet<>();
		PostCode postalCode1780 = new PostCode(1780);
		wemmelPostalCodes.add(postalCode1780);
		wemmel.setPostcodes(wemmelPostalCodes);
		municipalityRepository.save(wemmel);

		Municipality merchtem = new Municipality("Merchtem", 50.950049981318685, 4.242896126373626);
		Set<PostCode> merchtemPostalCodes = new HashSet<>();
		PostCode postalCode1785 = new PostCode(1785);
		merchtemPostalCodes.add(postalCode1785);
		merchtem.setPostcodes(merchtemPostalCodes);
		municipalityRepository.save(merchtem);

		Municipality affligem = new Municipality("Affligem", 50.90548233596491, 4.123397821929824);
		Set<PostCode> affligemPostalCodes = new HashSet<>();
		PostCode postalCode1790 = new PostCode(1790);
		affligemPostalCodes.add(postalCode1790);
		affligem.setPostcodes(affligemPostalCodes);
		municipalityRepository.save(affligem);

		Municipality vilvorde = new Municipality("Vilvorde", 50.92865870246479, 4.427089521830986);
		Set<PostCode> vilvordePostalCodes = new HashSet<>();
		PostCode postalCode1800 = new PostCode(1800);
		vilvordePostalCodes.add(postalCode1800);
		vilvorde.setPostcodes(vilvordePostalCodes);
		municipalityRepository.save(vilvorde);

		Municipality steenokkerzeel = new Municipality("Steenokkerzeel", 50.9224857625, 4.500816896428572);
		Set<PostCode> steenokkerzeelPostalCodes = new HashSet<>();
		PostCode postalCode1820 = new PostCode(1820);
		steenokkerzeelPostalCodes.add(postalCode1820);
		steenokkerzeel.setPostcodes(steenokkerzeelPostalCodes);
		municipalityRepository.save(steenokkerzeel);

		Municipality machelen = new Municipality("Machelen", 50.91169206677466, 4.439192189164866);
		Set<PostCode> machelenPostalCodes = new HashSet<>();
		PostCode postalCode1830 = new PostCode(1830);
		machelenPostalCodes.add(postalCode1830);
		PostCode postalCode1831 = new PostCode(1831);
		machelenPostalCodes.add(postalCode1831);
		machelen.setPostcodes(machelenPostalCodes);
		municipalityRepository.save(machelen);

		Municipality londerzeel = new Municipality("Londerzeel", 51.00606894130435, 4.278462241304347);
		Set<PostCode> londerzeelPostalCodes = new HashSet<>();
		PostCode postalCode1840 = new PostCode(1840);
		londerzeelPostalCodes.add(postalCode1840);
		londerzeel.setPostcodes(londerzeelPostalCodes);
		municipalityRepository.save(londerzeel);

		Municipality grimbergen = new Municipality("Grimbergen", 50.93950937807487, 4.3725758604278075);
		Set<PostCode> grimbergenPostalCodes = new HashSet<>();
		PostCode postalCode1850 = new PostCode(1850);
		grimbergenPostalCodes.add(postalCode1850);
		PostCode postalCode1851 = new PostCode(1851);
		grimbergenPostalCodes.add(postalCode1851);
		PostCode postalCode1852 = new PostCode(1852);
		grimbergenPostalCodes.add(postalCode1852);
		PostCode postalCode1853 = new PostCode(1853);
		grimbergenPostalCodes.add(postalCode1853);
		grimbergen.setPostcodes(grimbergenPostalCodes);
		municipalityRepository.save(grimbergen);

		Municipality meise = new Municipality("Meise", 50.93854476744186, 4.322314079069767);
		Set<PostCode> meisePostalCodes = new HashSet<>();
		PostCode postalCode1860 = new PostCode(1860);
		meisePostalCodes.add(postalCode1860);
		PostCode postalCode1861 = new PostCode(1861);
		meisePostalCodes.add(postalCode1861);
		meise.setPostcodes(meisePostalCodes);
		municipalityRepository.save(meise);

		Municipality kapelle_op_den_bos = new Municipality("Kapelle-op-den-Bos", 50.999378123255816, 4.351893467441861);
		Set<PostCode> kapelle_op_den_bosPostalCodes = new HashSet<>();
		PostCode postalCode1880 = new PostCode(1880);
		kapelle_op_den_bosPostalCodes.add(postalCode1880);
		kapelle_op_den_bos.setPostcodes(kapelle_op_den_bosPostalCodes);
		municipalityRepository.save(kapelle_op_den_bos);

		Municipality kampenhout = new Municipality("Kampenhout", 50.940196825, 4.574564784615385);
		Set<PostCode> kampenhoutPostalCodes = new HashSet<>();
		PostCode postalCode1910 = new PostCode(1910);
		kampenhoutPostalCodes.add(postalCode1910);
		kampenhout.setPostcodes(kampenhoutPostalCodes);
		municipalityRepository.save(kampenhout);

		Municipality zaventem = new Municipality("Zaventem", 50.879325419141914, 4.476754568481849);
		Set<PostCode> zaventemPostalCodes = new HashSet<>();
		PostCode postalCode1930 = new PostCode(1930);
		zaventemPostalCodes.add(postalCode1930);
		PostCode postalCode1932 = new PostCode(1932);
		zaventemPostalCodes.add(postalCode1932);
		PostCode postalCode1933 = new PostCode(1933);
		zaventemPostalCodes.add(postalCode1933);
		zaventem.setPostcodes(zaventemPostalCodes);
		municipalityRepository.save(zaventem);

		Municipality kraainem = new Municipality("Kraainem", 50.84783847993421, 4.4685187625);
		Set<PostCode> kraainemPostalCodes = new HashSet<>();
		PostCode postalCode1950 = new PostCode(1950);
		kraainemPostalCodes.add(postalCode1950);
		kraainem.setPostcodes(kraainemPostalCodes);
		municipalityRepository.save(kraainem);

		Municipality wezembeek_oppem = new Municipality("Wezembeek-Oppem", 50.8448751172, 4.492121254266666);
		Set<PostCode> wezembeek_oppemPostalCodes = new HashSet<>();
		PostCode postalCode1970 = new PostCode(1970);
		wezembeek_oppemPostalCodes.add(postalCode1970);
		wezembeek_oppem.setPostcodes(wezembeek_oppemPostalCodes);
		municipalityRepository.save(wezembeek_oppem);

		Municipality zemst = new Municipality("Zemst", 50.983427260493826, 4.4471049956790125);
		Set<PostCode> zemstPostalCodes = new HashSet<>();
		PostCode postalCode1980 = new PostCode(1980);
		zemstPostalCodes.add(postalCode1980);
		PostCode postalCode1981 = new PostCode(1981);
		zemstPostalCodes.add(postalCode1981);
		PostCode postalCode1982 = new PostCode(1982);
		zemstPostalCodes.add(postalCode1982);
		zemst.setPostcodes(zemstPostalCodes);
		municipalityRepository.save(zemst);

		Municipality louvain = new Municipality("Louvain", 50.88065779159758, 4.702749814221939);
		Set<PostCode> louvainPostalCodes = new HashSet<>();
		PostCode postalCode3000 = new PostCode(3000);
		louvainPostalCodes.add(postalCode3000);
		PostCode postalCode3001 = new PostCode(3001);
		louvainPostalCodes.add(postalCode3001);
		PostCode postalCode3010 = new PostCode(3010);
		louvainPostalCodes.add(postalCode3010);
		PostCode postalCode3012 = new PostCode(3012);
		louvainPostalCodes.add(postalCode3012);
		PostCode postalCode3018 = new PostCode(3018);
		louvainPostalCodes.add(postalCode3018);
		louvain.setPostcodes(louvainPostalCodes);
		municipalityRepository.save(louvain);

		Municipality herent = new Municipality("Herent", 50.906456092805755, 4.665937326258993);
		Set<PostCode> herentPostalCodes = new HashSet<>();
		PostCode postalCode3020 = new PostCode(3020);
		herentPostalCodes.add(postalCode3020);
		herent.setPostcodes(herentPostalCodes);
		municipalityRepository.save(herent);

		Municipality huldenberg = new Municipality("Huldenberg", 50.787885693678156, 4.623689728160919);
		Set<PostCode> huldenbergPostalCodes = new HashSet<>();
		PostCode postalCode3040 = new PostCode(3040);
		huldenbergPostalCodes.add(postalCode3040);
		huldenberg.setPostcodes(huldenbergPostalCodes);
		municipalityRepository.save(huldenberg);

		Municipality oud_heverlee = new Municipality("Oud-Heverlee", 50.83010287115384, 4.661097413461539);
		Set<PostCode> oud_heverleePostalCodes = new HashSet<>();
		PostCode postalCode3050 = new PostCode(3050);
		oud_heverleePostalCodes.add(postalCode3050);
		PostCode postalCode3051 = new PostCode(3051);
		oud_heverleePostalCodes.add(postalCode3051);
		PostCode postalCode3052 = new PostCode(3052);
		oud_heverleePostalCodes.add(postalCode3052);
		PostCode postalCode3053 = new PostCode(3053);
		oud_heverleePostalCodes.add(postalCode3053);
		PostCode postalCode3054 = new PostCode(3054);
		oud_heverleePostalCodes.add(postalCode3054);
		oud_heverlee.setPostcodes(oud_heverleePostalCodes);
		municipalityRepository.save(oud_heverlee);

		Municipality bertem = new Municipality("Bertem", 50.860283876, 4.6344649313333335);
		Set<PostCode> bertemPostalCodes = new HashSet<>();
		PostCode postalCode3060 = new PostCode(3060);
		bertemPostalCodes.add(postalCode3060);
		PostCode postalCode3061 = new PostCode(3061);
		bertemPostalCodes.add(postalCode3061);
		bertem.setPostcodes(bertemPostalCodes);
		municipalityRepository.save(bertem);

		Municipality kortenberg = new Municipality("Kortenberg", 50.88197799951923, 4.535192524038462);
		Set<PostCode> kortenbergPostalCodes = new HashSet<>();
		PostCode postalCode3070 = new PostCode(3070);
		kortenbergPostalCodes.add(postalCode3070);
		PostCode postalCode3071 = new PostCode(3071);
		kortenbergPostalCodes.add(postalCode3071);
		PostCode postalCode3078 = new PostCode(3078);
		kortenbergPostalCodes.add(postalCode3078);
		kortenberg.setPostcodes(kortenbergPostalCodes);
		municipalityRepository.save(kortenberg);

		Municipality tervuren = new Municipality("Tervuren", 50.8359688346932, 4.533038028159204);
		Set<PostCode> tervurenPostalCodes = new HashSet<>();
		PostCode postalCode3080 = new PostCode(3080);
		tervurenPostalCodes.add(postalCode3080);
		tervuren.setPostcodes(tervurenPostalCodes);
		municipalityRepository.save(tervuren);

		Municipality overijse = new Municipality("Overijse", 50.77128090069139, 4.542297692331867);
		Set<PostCode> overijsePostalCodes = new HashSet<>();
		PostCode postalCode3090 = new PostCode(3090);
		overijsePostalCodes.add(postalCode3090);
		overijse.setPostcodes(overijsePostalCodes);
		municipalityRepository.save(overijse);

		Municipality rotselaar = new Municipality("Rotselaar", 50.94881884113475, 4.7114186769503545);
		Set<PostCode> rotselaarPostalCodes = new HashSet<>();
		PostCode postalCode3110 = new PostCode(3110);
		rotselaarPostalCodes.add(postalCode3110);
		PostCode postalCode3111 = new PostCode(3111);
		rotselaarPostalCodes.add(postalCode3111);
		PostCode postalCode3118 = new PostCode(3118);
		rotselaarPostalCodes.add(postalCode3118);
		rotselaar.setPostcodes(rotselaarPostalCodes);
		municipalityRepository.save(rotselaar);

		Municipality tremelo = new Municipality("Tremelo", 50.99734942280702, 4.709594935087719);
		Set<PostCode> tremeloPostalCodes = new HashSet<>();
		PostCode postalCode3120 = new PostCode(3120);
		tremeloPostalCodes.add(postalCode3120);
		PostCode postalCode3128 = new PostCode(3128);
		tremeloPostalCodes.add(postalCode3128);
		tremelo.setPostcodes(tremeloPostalCodes);
		municipalityRepository.save(tremelo);

		Municipality begijnendijk = new Municipality("Begijnendijk", 51.004007148401826, 4.792445040182648);
		Set<PostCode> begijnendijkPostalCodes = new HashSet<>();
		PostCode postalCode3130 = new PostCode(3130);
		begijnendijkPostalCodes.add(postalCode3130);
		begijnendijk.setPostcodes(begijnendijkPostalCodes);
		municipalityRepository.save(begijnendijk);

		Municipality keerbergen = new Municipality("Keerbergen", 51.00883488220859, 4.653940147239264);
		Set<PostCode> keerbergenPostalCodes = new HashSet<>();
		PostCode postalCode3140 = new PostCode(3140);
		keerbergenPostalCodes.add(postalCode3140);
		keerbergen.setPostcodes(keerbergenPostalCodes);
		municipalityRepository.save(keerbergen);

		Municipality haacht = new Municipality("Haacht", 50.973765703571424, 4.627660939285714);
		Set<PostCode> haachtPostalCodes = new HashSet<>();
		PostCode postalCode3150 = new PostCode(3150);
		haachtPostalCodes.add(postalCode3150);
		haacht.setPostcodes(haachtPostalCodes);
		municipalityRepository.save(haacht);

		Municipality boortmeerbeek = new Municipality("Boortmeerbeek", 50.97581908301887, 4.572526358490566);
		Set<PostCode> boortmeerbeekPostalCodes = new HashSet<>();
		PostCode postalCode3190 = new PostCode(3190);
		boortmeerbeekPostalCodes.add(postalCode3190);
		PostCode postalCode3191 = new PostCode(3191);
		boortmeerbeekPostalCodes.add(postalCode3191);
		boortmeerbeek.setPostcodes(boortmeerbeekPostalCodes);
		municipalityRepository.save(boortmeerbeek);

		Municipality aarschot = new Municipality("Aarschot", 50.98712578349938, 4.831929696606476);
		Set<PostCode> aarschotPostalCodes = new HashSet<>();
		PostCode postalCode3200 = new PostCode(3200);
		aarschotPostalCodes.add(postalCode3200);
		PostCode postalCode3201 = new PostCode(3201);
		aarschotPostalCodes.add(postalCode3201);
		PostCode postalCode3202 = new PostCode(3202);
		aarschotPostalCodes.add(postalCode3202);
		aarschot.setPostcodes(aarschotPostalCodes);
		municipalityRepository.save(aarschot);

		Municipality lubbeek = new Municipality("Lubbeek", 50.89259458906753, 4.80875174437299);
		Set<PostCode> lubbeekPostalCodes = new HashSet<>();
		PostCode postalCode3210 = new PostCode(3210);
		lubbeekPostalCodes.add(postalCode3210);
		PostCode postalCode3211 = new PostCode(3211);
		lubbeekPostalCodes.add(postalCode3211);
		PostCode postalCode3212 = new PostCode(3212);
		lubbeekPostalCodes.add(postalCode3212);
		lubbeek.setPostcodes(lubbeekPostalCodes);
		municipalityRepository.save(lubbeek);

		Municipality holsbeek = new Municipality("Holsbeek", 50.91664035294118, 4.771396915294117);
		Set<PostCode> holsbeekPostalCodes = new HashSet<>();
		PostCode postalCode3220 = new PostCode(3220);
		holsbeekPostalCodes.add(postalCode3220);
		PostCode postalCode3221 = new PostCode(3221);
		holsbeekPostalCodes.add(postalCode3221);
		holsbeek.setPostcodes(holsbeekPostalCodes);
		municipalityRepository.save(holsbeek);

		Municipality montaigu_zichem = new Municipality("Montaigu-Zichem", 50.976412754477614, 4.966570879104478);
		Set<PostCode> montaigu_zichemPostalCodes = new HashSet<>();
		PostCode postalCode3270 = new PostCode(3270);
		montaigu_zichemPostalCodes.add(postalCode3270);
		PostCode postalCode3271 = new PostCode(3271);
		montaigu_zichemPostalCodes.add(postalCode3271);
		PostCode postalCode3272 = new PostCode(3272);
		montaigu_zichemPostalCodes.add(postalCode3272);
		montaigu_zichem.setPostcodes(montaigu_zichemPostalCodes);
		municipalityRepository.save(montaigu_zichem);

		Municipality diest = new Municipality("Diest", 50.99690219464179, 5.070392141626866);
		Set<PostCode> diestPostalCodes = new HashSet<>();
		PostCode postalCode3290 = new PostCode(3290);
		diestPostalCodes.add(postalCode3290);
		PostCode postalCode3293 = new PostCode(3293);
		diestPostalCodes.add(postalCode3293);
		PostCode postalCode3294 = new PostCode(3294);
		diestPostalCodes.add(postalCode3294);
		diest.setPostcodes(diestPostalCodes);
		municipalityRepository.save(diest);

		Municipality tirlemont = new Municipality("Tirlemont", 50.81002850607267, 4.939256788443671);
		Set<PostCode> tirlemontPostalCodes = new HashSet<>();
		PostCode postalCode3300 = new PostCode(3300);
		tirlemontPostalCodes.add(postalCode3300);
		tirlemont.setPostcodes(tirlemontPostalCodes);
		municipalityRepository.save(tirlemont);

		Municipality hoegaarden = new Municipality("Hoegaarden", 50.77969916598707, 4.878990616011935);
		Set<PostCode> hoegaardenPostalCodes = new HashSet<>();
		PostCode postalCode3320 = new PostCode(3320);
		hoegaardenPostalCodes.add(postalCode3320);
		PostCode postalCode3321 = new PostCode(3321);
		hoegaardenPostalCodes.add(postalCode3321);
		hoegaarden.setPostcodes(hoegaardenPostalCodes);
		municipalityRepository.save(hoegaarden);

		Municipality linter = new Municipality("Linter", 50.8291537, 5.0409717);
		Set<PostCode> linterPostalCodes = new HashSet<>();
		PostCode postalCode3350 = new PostCode(3350);
		linterPostalCodes.add(postalCode3350);
		linter.setPostcodes(linterPostalCodes);
		municipalityRepository.save(linter);

		Municipality bierbeek = new Municipality("Bierbeek", 50.84373272884849, 4.7674781352);
		Set<PostCode> bierbeekPostalCodes = new HashSet<>();
		PostCode postalCode3360 = new PostCode(3360);
		bierbeekPostalCodes.add(postalCode3360);
		bierbeek.setPostcodes(bierbeekPostalCodes);
		municipalityRepository.save(bierbeek);

		Municipality boutersem = new Municipality("Boutersem", 50.83896800576718, 4.845286508401567);
		Set<PostCode> boutersemPostalCodes = new HashSet<>();
		PostCode postalCode3370 = new PostCode(3370);
		boutersemPostalCodes.add(postalCode3370);
		boutersem.setPostcodes(boutersemPostalCodes);
		municipalityRepository.save(boutersem);

		Municipality glabbeek = new Municipality("Glabbeek", 50.86003154852624, 4.951501915815959);
		Set<PostCode> glabbeekPostalCodes = new HashSet<>();
		PostCode postalCode3380 = new PostCode(3380);
		glabbeekPostalCodes.add(postalCode3380);
		PostCode postalCode3381 = new PostCode(3381);
		glabbeekPostalCodes.add(postalCode3381);
		PostCode postalCode3384 = new PostCode(3384);
		glabbeekPostalCodes.add(postalCode3384);
		glabbeek.setPostcodes(glabbeekPostalCodes);
		municipalityRepository.save(glabbeek);

		Municipality tielt_winge = new Municipality("Tielt-Winge", 50.91149555886214, 4.879641476805252);
		Set<PostCode> tielt_wingePostalCodes = new HashSet<>();
		PostCode postalCode3390 = new PostCode(3390);
		tielt_wingePostalCodes.add(postalCode3390);
		PostCode postalCode3391 = new PostCode(3391);
		tielt_wingePostalCodes.add(postalCode3391);
		tielt_winge.setPostcodes(tielt_wingePostalCodes);
		municipalityRepository.save(tielt_winge);

		Municipality landen = new Municipality("Landen", 50.75959959752066, 5.062079159504132);
		Set<PostCode> landenPostalCodes = new HashSet<>();
		PostCode postalCode3400 = new PostCode(3400);
		landenPostalCodes.add(postalCode3400);
		PostCode postalCode3401 = new PostCode(3401);
		landenPostalCodes.add(postalCode3401);
		PostCode postalCode3404 = new PostCode(3404);
		landenPostalCodes.add(postalCode3404);
		landen.setPostcodes(landenPostalCodes);
		municipalityRepository.save(landen);

		Municipality léau = new Municipality("Léau", 50.84106254553571, 5.093627569642858);
		Set<PostCode> léauPostalCodes = new HashSet<>();
		PostCode postalCode3440 = new PostCode(3440);
		léauPostalCodes.add(postalCode3440);
		léau.setPostcodes(léauPostalCodes);
		municipalityRepository.save(léau);

		Municipality geetbets = new Municipality("Geetbets", 50.87750011254613, 5.120315811439114);
		Set<PostCode> geetbetsPostalCodes = new HashSet<>();
		PostCode postalCode3450 = new PostCode(3450);
		geetbetsPostalCodes.add(postalCode3450);
		PostCode postalCode3454 = new PostCode(3454);
		geetbetsPostalCodes.add(postalCode3454);
		geetbets.setPostcodes(geetbetsPostalCodes);
		municipalityRepository.save(geetbets);

		Municipality bekkevoort = new Municipality("Bekkevoort", 50.944710285245904, 4.984304099180328);
		Set<PostCode> bekkevoortPostalCodes = new HashSet<>();
		PostCode postalCode3460 = new PostCode(3460);
		bekkevoortPostalCodes.add(postalCode3460);
		PostCode postalCode3461 = new PostCode(3461);
		bekkevoortPostalCodes.add(postalCode3461);
		bekkevoort.setPostcodes(bekkevoortPostalCodes);
		municipalityRepository.save(bekkevoort);

		Municipality kortenaken = new Municipality("Kortenaken", 50.877698994736846, 5.043572865789474);
		Set<PostCode> kortenakenPostalCodes = new HashSet<>();
		PostCode postalCode3470 = new PostCode(3470);
		kortenakenPostalCodes.add(postalCode3470);
		PostCode postalCode3471 = new PostCode(3471);
		kortenakenPostalCodes.add(postalCode3471);
		PostCode postalCode3472 = new PostCode(3472);
		kortenakenPostalCodes.add(postalCode3472);
		PostCode postalCode3473 = new PostCode(3473);
		kortenakenPostalCodes.add(postalCode3473);
		kortenaken.setPostcodes(kortenakenPostalCodes);
		municipalityRepository.save(kortenaken);

		Municipality hasselt = new Municipality("Hasselt", 50.92928774845785, 5.3266594075394105);
		Set<PostCode> hasseltPostalCodes = new HashSet<>();
		PostCode postalCode3500 = new PostCode(3500);
		hasseltPostalCodes.add(postalCode3500);
		PostCode postalCode3501 = new PostCode(3501);
		hasseltPostalCodes.add(postalCode3501);
		PostCode postalCode3510 = new PostCode(3510);
		hasseltPostalCodes.add(postalCode3510);
		PostCode postalCode3511 = new PostCode(3511);
		hasseltPostalCodes.add(postalCode3511);
		PostCode postalCode3512 = new PostCode(3512);
		hasseltPostalCodes.add(postalCode3512);
		hasselt.setPostcodes(hasseltPostalCodes);
		municipalityRepository.save(hasselt);

		Municipality zonhoven = new Municipality("Zonhoven", 50.989738428548996, 5.371616088617035);
		Set<PostCode> zonhovenPostalCodes = new HashSet<>();
		PostCode postalCode3520 = new PostCode(3520);
		zonhovenPostalCodes.add(postalCode3520);
		zonhoven.setPostcodes(zonhovenPostalCodes);
		municipalityRepository.save(zonhoven);

		Municipality houthalen_helchteren = new Municipality("Houthalen-Helchteren", 51.029818059990696, 5.425026907490114);
		Set<PostCode> houthalen_helchterenPostalCodes = new HashSet<>();
		PostCode postalCode3530 = new PostCode(3530);
		houthalen_helchterenPostalCodes.add(postalCode3530);
		houthalen_helchteren.setPostcodes(houthalen_helchterenPostalCodes);
		municipalityRepository.save(houthalen_helchteren);

		Municipality herck_la_ville = new Municipality("Herck-la-Ville", 50.93249045993364, 5.169814931771049);
		Set<PostCode> herck_la_villePostalCodes = new HashSet<>();
		PostCode postalCode3540 = new PostCode(3540);
		herck_la_villePostalCodes.add(postalCode3540);
		herck_la_ville.setPostcodes(herck_la_villePostalCodes);
		municipalityRepository.save(herck_la_ville);

		Municipality halen = new Municipality("Halen", 50.975056184563755, 5.113208597091723);
		Set<PostCode> halenPostalCodes = new HashSet<>();
		PostCode postalCode3545 = new PostCode(3545);
		halenPostalCodes.add(postalCode3545);
		halen.setPostcodes(halenPostalCodes);
		municipalityRepository.save(halen);

		Municipality heusden_zolder = new Municipality("Heusden-Zolder", 51.02504132715655, 5.291495774391743);
		Set<PostCode> heusden_zolderPostalCodes = new HashSet<>();
		PostCode postalCode3550 = new PostCode(3550);
		heusden_zolderPostalCodes.add(postalCode3550);
		heusden_zolder.setPostcodes(heusden_zolderPostalCodes);
		municipalityRepository.save(heusden_zolder);

		Municipality lummen = new Municipality("Lummen", 50.99733587337506, 5.166401400674049);
		Set<PostCode> lummenPostalCodes = new HashSet<>();
		PostCode postalCode3560 = new PostCode(3560);
		lummenPostalCodes.add(postalCode3560);
		lummen.setPostcodes(lummenPostalCodes);
		municipalityRepository.save(lummen);

		Municipality alken = new Municipality("Alken", 50.872314160328436, 5.294446461019879);
		Set<PostCode> alkenPostalCodes = new HashSet<>();
		PostCode postalCode3570 = new PostCode(3570);
		alkenPostalCodes.add(postalCode3570);
		alken.setPostcodes(alkenPostalCodes);
		municipalityRepository.save(alken);

		Municipality beringen = new Municipality("Beringen", 51.04840348262799, 5.224276542866894);
		Set<PostCode> beringenPostalCodes = new HashSet<>();
		PostCode postalCode3580 = new PostCode(3580);
		beringenPostalCodes.add(postalCode3580);
		PostCode postalCode3581 = new PostCode(3581);
		beringenPostalCodes.add(postalCode3581);
		PostCode postalCode3582 = new PostCode(3582);
		beringenPostalCodes.add(postalCode3582);
		PostCode postalCode3583 = new PostCode(3583);
		beringenPostalCodes.add(postalCode3583);
		beringen.setPostcodes(beringenPostalCodes);
		municipalityRepository.save(beringen);

		Municipality diepenbeek = new Municipality("Diepenbeek", 50.91281820651584, 5.41715371280543);
		Set<PostCode> diepenbeekPostalCodes = new HashSet<>();
		PostCode postalCode3590 = new PostCode(3590);
		diepenbeekPostalCodes.add(postalCode3590);
		diepenbeek.setPostcodes(diepenbeekPostalCodes);
		municipalityRepository.save(diepenbeek);

		Municipality genk = new Municipality("Genk", 50.97568803820777, 5.495903552367994);
		Set<PostCode> genkPostalCodes = new HashSet<>();
		PostCode postalCode3600 = new PostCode(3600);
		genkPostalCodes.add(postalCode3600);
		genk.setPostcodes(genkPostalCodes);
		municipalityRepository.save(genk);

		Municipality lanaken = new Municipality("Lanaken", 50.88708618438494, 5.643505135034465);
		Set<PostCode> lanakenPostalCodes = new HashSet<>();
		PostCode postalCode3620 = new PostCode(3620);
		lanakenPostalCodes.add(postalCode3620);
		PostCode postalCode3621 = new PostCode(3621);
		lanakenPostalCodes.add(postalCode3621);
		lanaken.setPostcodes(lanakenPostalCodes);
		municipalityRepository.save(lanaken);

		Municipality maasmechelen = new Municipality("Maasmechelen", 50.97378436671635, 5.694233015039337);
		Set<PostCode> maasmechelenPostalCodes = new HashSet<>();
		PostCode postalCode3630 = new PostCode(3630);
		maasmechelenPostalCodes.add(postalCode3630);
		PostCode postalCode3631 = new PostCode(3631);
		maasmechelenPostalCodes.add(postalCode3631);
		maasmechelen.setPostcodes(maasmechelenPostalCodes);
		municipalityRepository.save(maasmechelen);

		Municipality kinrooi = new Municipality("Kinrooi", 51.14963842098214, 5.763941010364906);
		Set<PostCode> kinrooiPostalCodes = new HashSet<>();
		PostCode postalCode3640 = new PostCode(3640);
		kinrooiPostalCodes.add(postalCode3640);
		kinrooi.setPostcodes(kinrooiPostalCodes);
		municipalityRepository.save(kinrooi);

		Municipality dilsen_stokkem = new Municipality("Dilsen-Stokkem", 51.03512273217608, 5.724843787449233);
		Set<PostCode> dilsen_stokkemPostalCodes = new HashSet<>();
		PostCode postalCode3650 = new PostCode(3650);
		dilsen_stokkemPostalCodes.add(postalCode3650);
		dilsen_stokkem.setPostcodes(dilsen_stokkemPostalCodes);
		municipalityRepository.save(dilsen_stokkem);

		Municipality oudsbergen = new Municipality("Oudsbergen", 51.04151112473605, 5.579139445500251);
		Set<PostCode> oudsbergenPostalCodes = new HashSet<>();
		PostCode postalCode3660 = new PostCode(3660);
		oudsbergenPostalCodes.add(postalCode3660);
		PostCode postalCode3670 = new PostCode(3670);
		oudsbergenPostalCodes.add(postalCode3670);
		oudsbergen.setPostcodes(oudsbergenPostalCodes);
		municipalityRepository.save(oudsbergen);

		Municipality as = new Municipality("As", 51.00095255, 5.571983574462132);
		Set<PostCode> asPostalCodes = new HashSet<>();
		PostCode postalCode3665 = new PostCode(3665);
		asPostalCodes.add(postalCode3665);
		PostCode postalCode3668 = new PostCode(3668);
		asPostalCodes.add(postalCode3668);
		as.setPostcodes(asPostalCodes);
		municipalityRepository.save(as);

		Municipality maaseik = new Municipality("Maaseik", 51.09040456607557, 5.727090163768262);
		Set<PostCode> maaseikPostalCodes = new HashSet<>();
		PostCode postalCode3680 = new PostCode(3680);
		maaseikPostalCodes.add(postalCode3680);
		maaseik.setPostcodes(maaseikPostalCodes);
		municipalityRepository.save(maaseik);

		Municipality zutendaal = new Municipality("Zutendaal", 50.936747981317055, 5.572805809211986);
		Set<PostCode> zutendaalPostalCodes = new HashSet<>();
		PostCode postalCode3690 = new PostCode(3690);
		zutendaalPostalCodes.add(postalCode3690);
		zutendaal.setPostcodes(zutendaalPostalCodes);
		municipalityRepository.save(zutendaal);

		Municipality tongres = new Municipality("Tongres", 50.78007023145266, 5.468951073638133);
		Set<PostCode> tongresPostalCodes = new HashSet<>();
		PostCode postalCode3700 = new PostCode(3700);
		tongresPostalCodes.add(postalCode3700);
		tongres.setPostcodes(tongresPostalCodes);
		municipalityRepository.save(tongres);

		Municipality herstappe = new Municipality("Herstappe", 50.72731705, 5.42569465);
		Set<PostCode> herstappePostalCodes = new HashSet<>();
		PostCode postalCode3717 = new PostCode(3717);
		herstappePostalCodes.add(postalCode3717);
		herstappe.setPostcodes(herstappePostalCodes);
		municipalityRepository.save(herstappe);

		Municipality kortessem = new Municipality("Kortessem", 50.85592749023569, 5.3844825474747475);
		Set<PostCode> kortessemPostalCodes = new HashSet<>();
		PostCode postalCode3720 = new PostCode(3720);
		kortessemPostalCodes.add(postalCode3720);
		PostCode postalCode3721 = new PostCode(3721);
		kortessemPostalCodes.add(postalCode3721);
		PostCode postalCode3722 = new PostCode(3722);
		kortessemPostalCodes.add(postalCode3722);
		PostCode postalCode3723 = new PostCode(3723);
		kortessemPostalCodes.add(postalCode3723);
		PostCode postalCode3724 = new PostCode(3724);
		kortessemPostalCodes.add(postalCode3724);
		kortessem.setPostcodes(kortessemPostalCodes);
		municipalityRepository.save(kortessem);

		Municipality hoeselt = new Municipality("Hoeselt", 50.85747457931571, 5.470669245412131);
		Set<PostCode> hoeseltPostalCodes = new HashSet<>();
		PostCode postalCode3730 = new PostCode(3730);
		hoeseltPostalCodes.add(postalCode3730);
		PostCode postalCode3732 = new PostCode(3732);
		hoeseltPostalCodes.add(postalCode3732);
		hoeselt.setPostcodes(hoeseltPostalCodes);
		municipalityRepository.save(hoeselt);

		Municipality bilzen = new Municipality("Bilzen", 50.87189482024039, 5.528968782140832);
		Set<PostCode> bilzenPostalCodes = new HashSet<>();
		PostCode postalCode3740 = new PostCode(3740);
		bilzenPostalCodes.add(postalCode3740);
		PostCode postalCode3742 = new PostCode(3742);
		bilzenPostalCodes.add(postalCode3742);
		PostCode postalCode3746 = new PostCode(3746);
		bilzenPostalCodes.add(postalCode3746);
		bilzen.setPostcodes(bilzenPostalCodes);
		municipalityRepository.save(bilzen);

		Municipality riemst = new Municipality("Riemst", 50.80708024636616, 5.598455060146942);
		Set<PostCode> riemstPostalCodes = new HashSet<>();
		PostCode postalCode3770 = new PostCode(3770);
		riemstPostalCodes.add(postalCode3770);
		riemst.setPostcodes(riemstPostalCodes);
		municipalityRepository.save(riemst);

		Municipality fourons = new Municipality("Fourons", 50.75417646952191, 5.757027666533864);
		Set<PostCode> fouronsPostalCodes = new HashSet<>();
		PostCode postalCode3790 = new PostCode(3790);
		fouronsPostalCodes.add(postalCode3790);
		PostCode postalCode3791 = new PostCode(3791);
		fouronsPostalCodes.add(postalCode3791);
		PostCode postalCode3792 = new PostCode(3792);
		fouronsPostalCodes.add(postalCode3792);
		PostCode postalCode3793 = new PostCode(3793);
		fouronsPostalCodes.add(postalCode3793);
		PostCode postalCode3798 = new PostCode(3798);
		fouronsPostalCodes.add(postalCode3798);
		fourons.setPostcodes(fouronsPostalCodes);
		municipalityRepository.save(fourons);

		Municipality saint_trond = new Municipality("Saint-Trond", 50.81134846424682, 5.224775216959064);
		Set<PostCode> saint_trondPostalCodes = new HashSet<>();
		PostCode postalCode3800 = new PostCode(3800);
		saint_trondPostalCodes.add(postalCode3800);
		PostCode postalCode3803 = new PostCode(3803);
		saint_trondPostalCodes.add(postalCode3803);
		PostCode postalCode3806 = new PostCode(3806);
		saint_trondPostalCodes.add(postalCode3806);
		saint_trond.setPostcodes(saint_trondPostalCodes);
		municipalityRepository.save(saint_trond);

		Municipality wellen = new Municipality("Wellen", 50.84899823206484, 5.335360615997181);
		Set<PostCode> wellenPostalCodes = new HashSet<>();
		PostCode postalCode3830 = new PostCode(3830);
		wellenPostalCodes.add(postalCode3830);
		PostCode postalCode3831 = new PostCode(3831);
		wellenPostalCodes.add(postalCode3831);
		PostCode postalCode3832 = new PostCode(3832);
		wellenPostalCodes.add(postalCode3832);
		wellen.setPostcodes(wellenPostalCodes);
		municipalityRepository.save(wellen);

		Municipality looz = new Municipality("Looz", 50.80354779809445, 5.320265191963546);
		Set<PostCode> loozPostalCodes = new HashSet<>();
		PostCode postalCode3840 = new PostCode(3840);
		loozPostalCodes.add(postalCode3840);
		looz.setPostcodes(loozPostalCodes);
		municipalityRepository.save(looz);

		Municipality nieuwerkerken = new Municipality("Nieuwerkerken", 50.88061020388059, 5.22868755597015);
		Set<PostCode> nieuwerkerkenPostalCodes = new HashSet<>();
		PostCode postalCode3850 = new PostCode(3850);
		nieuwerkerkenPostalCodes.add(postalCode3850);
		nieuwerkerken.setPostcodes(nieuwerkerkenPostalCodes);
		municipalityRepository.save(nieuwerkerken);

		Municipality heers = new Municipality("Heers", 50.76044699776951, 5.304562527509294);
		Set<PostCode> heersPostalCodes = new HashSet<>();
		PostCode postalCode3870 = new PostCode(3870);
		heersPostalCodes.add(postalCode3870);
		heers.setPostcodes(heersPostalCodes);
		municipalityRepository.save(heers);

		Municipality gingelom = new Municipality("Gingelom", 50.73077086666667, 5.167856779999999);
		Set<PostCode> gingelomPostalCodes = new HashSet<>();
		PostCode postalCode3890 = new PostCode(3890);
		gingelomPostalCodes.add(postalCode3890);
		PostCode postalCode3891 = new PostCode(3891);
		gingelomPostalCodes.add(postalCode3891);
		gingelom.setPostcodes(gingelomPostalCodes);
		municipalityRepository.save(gingelom);

		Municipality pelt = new Municipality("Pelt", 51.207926641585345, 5.410451789351138);
		Set<PostCode> peltPostalCodes = new HashSet<>();
		PostCode postalCode3900 = new PostCode(3900);
		peltPostalCodes.add(postalCode3900);
		PostCode postalCode3910 = new PostCode(3910);
		peltPostalCodes.add(postalCode3910);
		pelt.setPostcodes(peltPostalCodes);
		municipalityRepository.save(pelt);

		Municipality lommel = new Municipality("Lommel", 51.227258805862625, 5.317288494434703);
		Set<PostCode> lommelPostalCodes = new HashSet<>();
		PostCode postalCode3920 = new PostCode(3920);
		lommelPostalCodes.add(postalCode3920);
		lommel.setPostcodes(lommelPostalCodes);
		municipalityRepository.save(lommel);

		Municipality hamont_achel = new Municipality("Hamont-Achel", 51.25299308242597, 5.516452149530059);
		Set<PostCode> hamont_achelPostalCodes = new HashSet<>();
		PostCode postalCode3930 = new PostCode(3930);
		hamont_achelPostalCodes.add(postalCode3930);
		hamont_achel.setPostcodes(hamont_achelPostalCodes);
		municipalityRepository.save(hamont_achel);

		Municipality hechtel_eksel = new Municipality("Hechtel-Eksel", 51.137053721081415, 5.374100104637505);
		Set<PostCode> hechtel_ekselPostalCodes = new HashSet<>();
		PostCode postalCode3940 = new PostCode(3940);
		hechtel_ekselPostalCodes.add(postalCode3940);
		PostCode postalCode3941 = new PostCode(3941);
		hechtel_ekselPostalCodes.add(postalCode3941);
		hechtel_eksel.setPostcodes(hechtel_ekselPostalCodes);
		municipalityRepository.save(hechtel_eksel);

		Municipality ham = new Municipality("Ham", 51.09877889646626, 5.157068862797546);
		Set<PostCode> hamPostalCodes = new HashSet<>();
		PostCode postalCode3945 = new PostCode(3945);
		hamPostalCodes.add(postalCode3945);
		ham.setPostcodes(hamPostalCodes);
		municipalityRepository.save(ham);

		Municipality bocholt = new Municipality("Bocholt", 51.1822000299824, 5.552730405085077);
		Set<PostCode> bocholtPostalCodes = new HashSet<>();
		PostCode postalCode3950 = new PostCode(3950);
		bocholtPostalCodes.add(postalCode3950);
		bocholt.setPostcodes(bocholtPostalCodes);
		municipalityRepository.save(bocholt);

		Municipality bree = new Municipality("Bree", 51.135884307568574, 5.610687745292922);
		Set<PostCode> breePostalCodes = new HashSet<>();
		PostCode postalCode3960 = new PostCode(3960);
		breePostalCodes.add(postalCode3960);
		bree.setPostcodes(breePostalCodes);
		municipalityRepository.save(bree);

		Municipality bourg_léopold = new Municipality("Bourg-Léopold", 51.12125877933107, 5.25664370817873);
		Set<PostCode> bourg_léopoldPostalCodes = new HashSet<>();
		PostCode postalCode3970 = new PostCode(3970);
		bourg_léopoldPostalCodes.add(postalCode3970);
		PostCode postalCode3971 = new PostCode(3971);
		bourg_léopoldPostalCodes.add(postalCode3971);
		bourg_léopold.setPostcodes(bourg_léopoldPostalCodes);
		municipalityRepository.save(bourg_léopold);

		Municipality tessenderlo = new Municipality("Tessenderlo", 51.06099565078382, 5.083652836016335);
		Set<PostCode> tessenderloPostalCodes = new HashSet<>();
		PostCode postalCode3980 = new PostCode(3980);
		tessenderloPostalCodes.add(postalCode3980);
		tessenderlo.setPostcodes(tessenderloPostalCodes);
		municipalityRepository.save(tessenderlo);

		Municipality peer = new Municipality("Peer", 51.133460143335874, 5.456353678720488);
		Set<PostCode> peerPostalCodes = new HashSet<>();
		PostCode postalCode3990 = new PostCode(3990);
		peerPostalCodes.add(postalCode3990);
		peer.setPostcodes(peerPostalCodes);
		municipalityRepository.save(peer);

		Municipality bruges = new Municipality("Bruges", 51.214836632191336, 3.220371497543633);
		Set<PostCode> brugesPostalCodes = new HashSet<>();
		PostCode postalCode8000 = new PostCode(8000);
		brugesPostalCodes.add(postalCode8000);
		PostCode postalCode8200 = new PostCode(8200);
		brugesPostalCodes.add(postalCode8200);
		PostCode postalCode8310 = new PostCode(8310);
		brugesPostalCodes.add(postalCode8310);
		PostCode postalCode8380 = new PostCode(8380);
		brugesPostalCodes.add(postalCode8380);
		bruges.setPostcodes(brugesPostalCodes);
		municipalityRepository.save(bruges);

		Municipality oostkamp = new Municipality("Oostkamp", 51.12674503623011, 3.246492423500612);
		Set<PostCode> oostkampPostalCodes = new HashSet<>();
		PostCode postalCode8020 = new PostCode(8020);
		oostkampPostalCodes.add(postalCode8020);
		oostkamp.setPostcodes(oostkampPostalCodes);
		municipalityRepository.save(oostkamp);

		Municipality zedelgem = new Municipality("Zedelgem", 51.146995713529414, 3.17593271);
		Set<PostCode> zedelgemPostalCodes = new HashSet<>();
		PostCode postalCode8210 = new PostCode(8210);
		zedelgemPostalCodes.add(postalCode8210);
		PostCode postalCode8211 = new PostCode(8211);
		zedelgemPostalCodes.add(postalCode8211);
		zedelgem.setPostcodes(zedelgemPostalCodes);
		municipalityRepository.save(zedelgem);

		Municipality knokke_heist = new Municipality("Knokke-Heist", 51.34634507381568, 3.2911197499569336);
		Set<PostCode> knokke_heistPostalCodes = new HashSet<>();
		PostCode postalCode8300 = new PostCode(8300);
		knokke_heistPostalCodes.add(postalCode8300);
		PostCode postalCode8301 = new PostCode(8301);
		knokke_heistPostalCodes.add(postalCode8301);
		knokke_heist.setPostcodes(knokke_heistPostalCodes);
		municipalityRepository.save(knokke_heist);

		Municipality damme = new Municipality("Damme", 51.2358514995338, 3.296486246620047);
		Set<PostCode> dammePostalCodes = new HashSet<>();
		PostCode postalCode8340 = new PostCode(8340);
		dammePostalCodes.add(postalCode8340);
		damme.setPostcodes(dammePostalCodes);
		municipalityRepository.save(damme);

		Municipality blankenberge = new Municipality("Blankenberge", 51.309185743589744, 3.135036569230769);
		Set<PostCode> blankenbergePostalCodes = new HashSet<>();
		PostCode postalCode8370 = new PostCode(8370);
		blankenbergePostalCodes.add(postalCode8370);
		blankenberge.setPostcodes(blankenbergePostalCodes);
		municipalityRepository.save(blankenberge);

		Municipality zuienkerke = new Municipality("Zuienkerke", 51.25637407058824, 3.141626223529412);
		Set<PostCode> zuienkerkePostalCodes = new HashSet<>();
		PostCode postalCode8377 = new PostCode(8377);
		zuienkerkePostalCodes.add(postalCode8377);
		zuienkerke.setPostcodes(zuienkerkePostalCodes);
		municipalityRepository.save(zuienkerke);

		Municipality ostende = new Municipality("Ostende", 51.221050888418624, 2.9114949023470564);
		Set<PostCode> ostendePostalCodes = new HashSet<>();
		PostCode postalCode8400 = new PostCode(8400);
		ostendePostalCodes.add(postalCode8400);
		ostende.setPostcodes(ostendePostalCodes);
		municipalityRepository.save(ostende);

		Municipality de_haan = new Municipality("De Haan", 51.275083, 3.03169);
		Set<PostCode> de_haanPostalCodes = new HashSet<>();
		PostCode postalCode8420 = new PostCode(8420);
		de_haanPostalCodes.add(postalCode8420);
		PostCode postalCode8421 = new PostCode(8421);
		de_haanPostalCodes.add(postalCode8421);
		de_haan.setPostcodes(de_haanPostalCodes);
		municipalityRepository.save(de_haan);

		Municipality middelkerke = new Municipality("Middelkerke", 51.18255825957446, 2.820102170212766);
		Set<PostCode> middelkerkePostalCodes = new HashSet<>();
		PostCode postalCode8430 = new PostCode(8430);
		middelkerkePostalCodes.add(postalCode8430);
		PostCode postalCode8431 = new PostCode(8431);
		middelkerkePostalCodes.add(postalCode8431);
		PostCode postalCode8432 = new PostCode(8432);
		middelkerkePostalCodes.add(postalCode8432);
		PostCode postalCode8433 = new PostCode(8433);
		middelkerkePostalCodes.add(postalCode8433);
		PostCode postalCode8434 = new PostCode(8434);
		middelkerkePostalCodes.add(postalCode8434);
		middelkerke.setPostcodes(middelkerkePostalCodes);
		municipalityRepository.save(middelkerke);

		Municipality bredene = new Municipality("Bredene", 51.23019340247934, 2.964483180578512);
		Set<PostCode> bredenePostalCodes = new HashSet<>();
		PostCode postalCode8450 = new PostCode(8450);
		bredenePostalCodes.add(postalCode8450);
		bredene.setPostcodes(bredenePostalCodes);
		municipalityRepository.save(bredene);

		Municipality oudenburg = new Municipality("Oudenburg", 51.17879809032403, 3.0090671888838885);
		Set<PostCode> oudenburgPostalCodes = new HashSet<>();
		PostCode postalCode8460 = new PostCode(8460);
		oudenburgPostalCodes.add(postalCode8460);
		oudenburg.setPostcodes(oudenburgPostalCodes);
		municipalityRepository.save(oudenburg);

		Municipality gistel = new Municipality("Gistel", 51.153332436328625, 2.95854826300813);
		Set<PostCode> gistelPostalCodes = new HashSet<>();
		PostCode postalCode8470 = new PostCode(8470);
		gistelPostalCodes.add(postalCode8470);
		gistel.setPostcodes(gistelPostalCodes);
		municipalityRepository.save(gistel);

		Municipality ichtegem = new Municipality("Ichtegem", 51.0981742883071, 3.024057065210408);
		Set<PostCode> ichtegemPostalCodes = new HashSet<>();
		PostCode postalCode8480 = new PostCode(8480);
		ichtegemPostalCodes.add(postalCode8480);
		ichtegem.setPostcodes(ichtegemPostalCodes);
		municipalityRepository.save(ichtegem);

		Municipality jabbeke = new Municipality("Jabbeke", 51.182085026873565, 3.113656387218391);
		Set<PostCode> jabbekePostalCodes = new HashSet<>();
		PostCode postalCode8490 = new PostCode(8490);
		jabbekePostalCodes.add(postalCode8490);
		jabbeke.setPostcodes(jabbekePostalCodes);
		municipalityRepository.save(jabbeke);

		Municipality courtrai = new Municipality("Courtrai", 50.82351803199857, 3.276385979454177);
		Set<PostCode> courtraiPostalCodes = new HashSet<>();
		PostCode postalCode8500 = new PostCode(8500);
		courtraiPostalCodes.add(postalCode8500);
		PostCode postalCode8501 = new PostCode(8501);
		courtraiPostalCodes.add(postalCode8501);
		PostCode postalCode8510 = new PostCode(8510);
		courtraiPostalCodes.add(postalCode8510);
		PostCode postalCode8511 = new PostCode(8511);
		courtraiPostalCodes.add(postalCode8511);
		courtrai.setPostcodes(courtraiPostalCodes);
		municipalityRepository.save(courtrai);

		Municipality kuurne = new Municipality("Kuurne", 50.85482455251397, 3.2816632951582867);
		Set<PostCode> kuurnePostalCodes = new HashSet<>();
		PostCode postalCode8520 = new PostCode(8520);
		kuurnePostalCodes.add(postalCode8520);
		kuurne.setPostcodes(kuurnePostalCodes);
		municipalityRepository.save(kuurne);

		Municipality harelbeke = new Municipality("Harelbeke", 50.84742325508444, 3.3120726170918675);
		Set<PostCode> harelbekePostalCodes = new HashSet<>();
		PostCode postalCode8530 = new PostCode(8530);
		harelbekePostalCodes.add(postalCode8530);
		PostCode postalCode8531 = new PostCode(8531);
		harelbekePostalCodes.add(postalCode8531);
		harelbeke.setPostcodes(harelbekePostalCodes);
		municipalityRepository.save(harelbeke);

		Municipality deerlijk = new Municipality("Deerlijk", 50.84550993811678, 3.3637165833470397);
		Set<PostCode> deerlijkPostalCodes = new HashSet<>();
		PostCode postalCode8540 = new PostCode(8540);
		deerlijkPostalCodes.add(postalCode8540);
		deerlijk.setPostcodes(deerlijkPostalCodes);
		municipalityRepository.save(deerlijk);

		Municipality zwevegem = new Municipality("Zwevegem", 50.81629298522728, 3.339059905681818);
		Set<PostCode> zwevegemPostalCodes = new HashSet<>();
		PostCode postalCode8550 = new PostCode(8550);
		zwevegemPostalCodes.add(postalCode8550);
		PostCode postalCode8551 = new PostCode(8551);
		zwevegemPostalCodes.add(postalCode8551);
		PostCode postalCode8552 = new PostCode(8552);
		zwevegemPostalCodes.add(postalCode8552);
		PostCode postalCode8553 = new PostCode(8553);
		zwevegemPostalCodes.add(postalCode8553);
		PostCode postalCode8554 = new PostCode(8554);
		zwevegemPostalCodes.add(postalCode8554);
		zwevegem.setPostcodes(zwevegemPostalCodes);
		municipalityRepository.save(zwevegem);

		Municipality wevelgem = new Municipality("Wevelgem", 50.825034668801656, 3.176628247365702);
		Set<PostCode> wevelgemPostalCodes = new HashSet<>();
		PostCode postalCode8560 = new PostCode(8560);
		wevelgemPostalCodes.add(postalCode8560);
		wevelgem.setPostcodes(wevelgemPostalCodes);
		municipalityRepository.save(wevelgem);

		Municipality anzegem = new Municipality("Anzegem", 50.83666159667864, 3.403419375852783);
		Set<PostCode> anzegemPostalCodes = new HashSet<>();
		PostCode postalCode8570 = new PostCode(8570);
		anzegemPostalCodes.add(postalCode8570);
		PostCode postalCode8572 = new PostCode(8572);
		anzegemPostalCodes.add(postalCode8572);
		PostCode postalCode8573 = new PostCode(8573);
		anzegemPostalCodes.add(postalCode8573);
		anzegem.setPostcodes(anzegemPostalCodes);
		municipalityRepository.save(anzegem);

		Municipality avelgem = new Municipality("Avelgem", 50.77583964166667, 3.446367475);
		Set<PostCode> avelgemPostalCodes = new HashSet<>();
		PostCode postalCode8580 = new PostCode(8580);
		avelgemPostalCodes.add(postalCode8580);
		PostCode postalCode8581 = new PostCode(8581);
		avelgemPostalCodes.add(postalCode8581);
		PostCode postalCode8582 = new PostCode(8582);
		avelgemPostalCodes.add(postalCode8582);
		PostCode postalCode8583 = new PostCode(8583);
		avelgemPostalCodes.add(postalCode8583);
		avelgem.setPostcodes(avelgemPostalCodes);
		municipalityRepository.save(avelgem);

		Municipality espierres_helchin = new Municipality("Espierres-Helchin", 50.72572037333334, 3.3554140066666664);
		Set<PostCode> espierres_helchinPostalCodes = new HashSet<>();
		PostCode postalCode8587 = new PostCode(8587);
		espierres_helchinPostalCodes.add(postalCode8587);
		espierres_helchin.setPostcodes(espierres_helchinPostalCodes);
		municipalityRepository.save(espierres_helchin);

		Municipality dixmude = new Municipality("Dixmude", 51.05892354243986, 2.870336714089347);
		Set<PostCode> dixmudePostalCodes = new HashSet<>();
		PostCode postalCode8600 = new PostCode(8600);
		dixmudePostalCodes.add(postalCode8600);
		dixmude.setPostcodes(dixmudePostalCodes);
		municipalityRepository.save(dixmude);

		Municipality kortemark = new Municipality("Kortemark", 51.02732957463768, 2.999950722463768);
		Set<PostCode> kortemarkPostalCodes = new HashSet<>();
		PostCode postalCode8610 = new PostCode(8610);
		kortemarkPostalCodes.add(postalCode8610);
		kortemark.setPostcodes(kortemarkPostalCodes);
		municipalityRepository.save(kortemark);

		Municipality nieuport = new Municipality("Nieuport", 51.134935185125634, 2.742805671557789);
		Set<PostCode> nieuportPostalCodes = new HashSet<>();
		PostCode postalCode8620 = new PostCode(8620);
		nieuportPostalCodes.add(postalCode8620);
		nieuport.setPostcodes(nieuportPostalCodes);
		municipalityRepository.save(nieuport);

		Municipality furnes = new Municipality("Furnes", 51.03648392264151, 2.633065508301887);
		Set<PostCode> furnesPostalCodes = new HashSet<>();
		PostCode postalCode8630 = new PostCode(8630);
		furnesPostalCodes.add(postalCode8630);
		furnes.setPostcodes(furnesPostalCodes);
		municipalityRepository.save(furnes);

		Municipality vleteren = new Municipality("Vleteren", 50.92332574230769, 2.7551923307692308);
		Set<PostCode> vleterenPostalCodes = new HashSet<>();
		PostCode postalCode8640 = new PostCode(8640);
		vleterenPostalCodes.add(postalCode8640);
		vleteren.setPostcodes(vleterenPostalCodes);
		municipalityRepository.save(vleteren);

		Municipality lo_reninge = new Municipality("Lo-Reninge", 50.972414611627904, 2.7602057093023253);
		Set<PostCode> lo_reningePostalCodes = new HashSet<>();
		PostCode postalCode8647 = new PostCode(8647);
		lo_reningePostalCodes.add(postalCode8647);
		lo_reninge.setPostcodes(lo_reningePostalCodes);
		municipalityRepository.save(lo_reninge);

		Municipality houthulst = new Municipality("Houthulst", 50.975510135308376, 2.919171263920705);
		Set<PostCode> houthulstPostalCodes = new HashSet<>();
		PostCode postalCode8650 = new PostCode(8650);
		houthulstPostalCodes.add(postalCode8650);
		houthulst.setPostcodes(houthulstPostalCodes);
		municipalityRepository.save(houthulst);

		Municipality la_panne = new Municipality("La Panne", 51.095512049999996, 2.581164640676633);
		Set<PostCode> la_pannePostalCodes = new HashSet<>();
		PostCode postalCode8660 = new PostCode(8660);
		la_pannePostalCodes.add(postalCode8660);
		la_panne.setPostcodes(la_pannePostalCodes);
		municipalityRepository.save(la_panne);

		Municipality koksijde = new Municipality("Koksijde", 51.11636566101083, 2.6429919667870037);
		Set<PostCode> koksijdePostalCodes = new HashSet<>();
		PostCode postalCode8670 = new PostCode(8670);
		koksijdePostalCodes.add(postalCode8670);
		koksijde.setPostcodes(koksijdePostalCodes);
		municipalityRepository.save(koksijde);

		Municipality koekelare = new Municipality("Koekelare", 51.08875559617347, 2.9749253994897957);
		Set<PostCode> koekelarePostalCodes = new HashSet<>();
		PostCode postalCode8680 = new PostCode(8680);
		koekelarePostalCodes.add(postalCode8680);
		koekelare.setPostcodes(koekelarePostalCodes);
		municipalityRepository.save(koekelare);

		Municipality alveringem = new Municipality("Alveringem", 51.0022397409091, 2.7045519227272727);
		Set<PostCode> alveringemPostalCodes = new HashSet<>();
		PostCode postalCode8690 = new PostCode(8690);
		alveringemPostalCodes.add(postalCode8690);
		PostCode postalCode8691 = new PostCode(8691);
		alveringemPostalCodes.add(postalCode8691);
		alveringem.setPostcodes(alveringemPostalCodes);
		municipalityRepository.save(alveringem);

		Municipality tielt = new Municipality("Tielt", 50.98776224259259, 3.3490688332010583);
		Set<PostCode> tieltPostalCodes = new HashSet<>();
		PostCode postalCode8700 = new PostCode(8700);
		tieltPostalCodes.add(postalCode8700);
		tielt.setPostcodes(tieltPostalCodes);
		municipalityRepository.save(tielt);

		Municipality wielsbeke = new Municipality("Wielsbeke", 50.90749704832243, 3.36378889605402);
		Set<PostCode> wielsbekePostalCodes = new HashSet<>();
		PostCode postalCode8710 = new PostCode(8710);
		wielsbekePostalCodes.add(postalCode8710);
		wielsbeke.setPostcodes(wielsbekePostalCodes);
		municipalityRepository.save(wielsbeke);

		Municipality dentergem = new Municipality("Dentergem", 50.93951799656488, 3.3964521181570335);
		Set<PostCode> dentergemPostalCodes = new HashSet<>();
		PostCode postalCode8720 = new PostCode(8720);
		dentergemPostalCodes.add(postalCode8720);
		dentergem.setPostcodes(dentergemPostalCodes);
		municipalityRepository.save(dentergem);

		Municipality beernem = new Municipality("Beernem", 51.150632353333336, 3.3299282022222223);
		Set<PostCode> beernemPostalCodes = new HashSet<>();
		PostCode postalCode8730 = new PostCode(8730);
		beernemPostalCodes.add(postalCode8730);
		beernem.setPostcodes(beernemPostalCodes);
		municipalityRepository.save(beernem);

		Municipality pittem = new Municipality("Pittem", 51.001952672161174, 3.2501490413919414);
		Set<PostCode> pittemPostalCodes = new HashSet<>();
		PostCode postalCode8740 = new PostCode(8740);
		pittemPostalCodes.add(postalCode8740);
		pittem.setPostcodes(pittemPostalCodes);
		municipalityRepository.save(pittem);

		Municipality wingene = new Municipality("Wingene", 51.049184896603265, 3.231915265353261);
		Set<PostCode> wingenePostalCodes = new HashSet<>();
		PostCode postalCode8750 = new PostCode(8750);
		wingenePostalCodes.add(postalCode8750);
		wingene.setPostcodes(wingenePostalCodes);
		municipalityRepository.save(wingene);

		Municipality ruiselede = new Municipality("Ruiselede", 51.03853497692308, 3.3599164115384617);
		Set<PostCode> ruiseledePostalCodes = new HashSet<>();
		PostCode postalCode8755 = new PostCode(8755);
		ruiseledePostalCodes.add(postalCode8755);
		ruiselede.setPostcodes(ruiseledePostalCodes);
		municipalityRepository.save(ruiselede);

		Municipality meulebeke = new Municipality("Meulebeke", 50.94681883245954, 3.2996071068608415);
		Set<PostCode> meulebekePostalCodes = new HashSet<>();
		PostCode postalCode8760 = new PostCode(8760);
		meulebekePostalCodes.add(postalCode8760);
		meulebeke.setPostcodes(meulebekePostalCodes);
		municipalityRepository.save(meulebeke);

		Municipality ingelmunster = new Municipality("Ingelmunster", 50.91994261436088, 3.2574381317614964);
		Set<PostCode> ingelmunsterPostalCodes = new HashSet<>();
		PostCode postalCode8770 = new PostCode(8770);
		ingelmunsterPostalCodes.add(postalCode8770);
		ingelmunster.setPostcodes(ingelmunsterPostalCodes);
		municipalityRepository.save(ingelmunster);

		Municipality oostrozebeke = new Municipality("Oostrozebeke", 50.92594901181319, 3.3369787874375625);
		Set<PostCode> oostrozebekePostalCodes = new HashSet<>();
		PostCode postalCode8780 = new PostCode(8780);
		oostrozebekePostalCodes.add(postalCode8780);
		oostrozebeke.setPostcodes(oostrozebekePostalCodes);
		municipalityRepository.save(oostrozebeke);

		Municipality waregem = new Municipality("Waregem", 50.88257546305163, 3.423257735424979);
		Set<PostCode> waregemPostalCodes = new HashSet<>();
		PostCode postalCode8790 = new PostCode(8790);
		waregemPostalCodes.add(postalCode8790);
		PostCode postalCode8791 = new PostCode(8791);
		waregemPostalCodes.add(postalCode8791);
		PostCode postalCode8792 = new PostCode(8792);
		waregemPostalCodes.add(postalCode8792);
		PostCode postalCode8793 = new PostCode(8793);
		waregemPostalCodes.add(postalCode8793);
		waregem.setPostcodes(waregemPostalCodes);
		municipalityRepository.save(waregem);

		Municipality roulers = new Municipality("Roulers", 50.94805834067797, 3.1332793776029053);
		Set<PostCode> roulersPostalCodes = new HashSet<>();
		PostCode postalCode8800 = new PostCode(8800);
		roulersPostalCodes.add(postalCode8800);
		roulers.setPostcodes(roulersPostalCodes);
		municipalityRepository.save(roulers);

		Municipality lichtervelde = new Municipality("Lichtervelde", 51.01910135373666, 3.1448046402135232);
		Set<PostCode> lichterveldePostalCodes = new HashSet<>();
		PostCode postalCode8810 = new PostCode(8810);
		lichterveldePostalCodes.add(postalCode8810);
		lichtervelde.setPostcodes(lichterveldePostalCodes);
		municipalityRepository.save(lichtervelde);

		Municipality torhout = new Municipality("Torhout", 51.068606398823526, 3.095275208823529);
		Set<PostCode> torhoutPostalCodes = new HashSet<>();
		PostCode postalCode8820 = new PostCode(8820);
		torhoutPostalCodes.add(postalCode8820);
		torhout.setPostcodes(torhoutPostalCodes);
		municipalityRepository.save(torhout);

		Municipality hooglede = new Municipality("Hooglede", 50.98155684969278, 3.075748721313364);
		Set<PostCode> hoogledePostalCodes = new HashSet<>();
		PostCode postalCode8830 = new PostCode(8830);
		hoogledePostalCodes.add(postalCode8830);
		hooglede.setPostcodes(hoogledePostalCodes);
		municipalityRepository.save(hooglede);

		Municipality staden = new Municipality("Staden", 50.95225370681818, 3.044322365909091);
		Set<PostCode> stadenPostalCodes = new HashSet<>();
		PostCode postalCode8840 = new PostCode(8840);
		stadenPostalCodes.add(postalCode8840);
		staden.setPostcodes(stadenPostalCodes);
		municipalityRepository.save(staden);

		Municipality ardooie = new Municipality("Ardooie", 50.97303459508197, 3.1990726377049183);
		Set<PostCode> ardooiePostalCodes = new HashSet<>();
		PostCode postalCode8850 = new PostCode(8850);
		ardooiePostalCodes.add(postalCode8850);
		PostCode postalCode8851 = new PostCode(8851);
		ardooiePostalCodes.add(postalCode8851);
		ardooie.setPostcodes(ardooiePostalCodes);
		municipalityRepository.save(ardooie);

		Municipality lendelede = new Municipality("Lendelede", 50.890031044578315, 3.23100563313253);
		Set<PostCode> lendeledePostalCodes = new HashSet<>();
		PostCode postalCode8860 = new PostCode(8860);
		lendeledePostalCodes.add(postalCode8860);
		lendelede.setPostcodes(lendeledePostalCodes);
		municipalityRepository.save(lendelede);

		Municipality izegem = new Municipality("Izegem", 50.91374040164393, 3.2082969495526727);
		Set<PostCode> izegemPostalCodes = new HashSet<>();
		PostCode postalCode8870 = new PostCode(8870);
		izegemPostalCodes.add(postalCode8870);
		izegem.setPostcodes(izegemPostalCodes);
		municipalityRepository.save(izegem);

		Municipality ledegem = new Municipality("Ledegem", 50.867474927272724, 3.1532092045454547);
		Set<PostCode> ledegemPostalCodes = new HashSet<>();
		PostCode postalCode8880 = new PostCode(8880);
		ledegemPostalCodes.add(postalCode8880);
		ledegem.setPostcodes(ledegemPostalCodes);
		municipalityRepository.save(ledegem);

		Municipality moorslede = new Municipality("Moorslede", 50.861425739999994, 3.0906922333333333);
		Set<PostCode> moorsledePostalCodes = new HashSet<>();
		PostCode postalCode8890 = new PostCode(8890);
		moorsledePostalCodes.add(postalCode8890);
		moorslede.setPostcodes(moorsledePostalCodes);
		municipalityRepository.save(moorslede);

		Municipality ypres = new Municipality("Ypres", 50.8496948547426, 2.8794917370085122);
		Set<PostCode> ypresPostalCodes = new HashSet<>();
		PostCode postalCode8900 = new PostCode(8900);
		ypresPostalCodes.add(postalCode8900);
		PostCode postalCode8902 = new PostCode(8902);
		ypresPostalCodes.add(postalCode8902);
		PostCode postalCode8904 = new PostCode(8904);
		ypresPostalCodes.add(postalCode8904);
		PostCode postalCode8906 = new PostCode(8906);
		ypresPostalCodes.add(postalCode8906);
		PostCode postalCode8908 = new PostCode(8908);
		ypresPostalCodes.add(postalCode8908);
		ypres.setPostcodes(ypresPostalCodes);
		municipalityRepository.save(ypres);

		Municipality langemark_poelkapelle = new Municipality("Langemark-Poelkapelle", 50.917297697560976, 2.9231713146341463);
		Set<PostCode> langemark_poelkapellePostalCodes = new HashSet<>();
		PostCode postalCode8920 = new PostCode(8920);
		langemark_poelkapellePostalCodes.add(postalCode8920);
		langemark_poelkapelle.setPostcodes(langemark_poelkapellePostalCodes);
		municipalityRepository.save(langemark_poelkapelle);

		Municipality menin = new Municipality("Menin", 50.78923495420168, 3.1761663);
		Set<PostCode> meninPostalCodes = new HashSet<>();
		PostCode postalCode8930 = new PostCode(8930);
		meninPostalCodes.add(postalCode8930);
		menin.setPostcodes(meninPostalCodes);
		municipalityRepository.save(menin);

		Municipality wervik = new Municipality("Wervik", 50.797028695522386, 3.0556006368159205);
		Set<PostCode> wervikPostalCodes = new HashSet<>();
		PostCode postalCode8940 = new PostCode(8940);
		wervikPostalCodes.add(postalCode8940);
		wervik.setPostcodes(wervikPostalCodes);
		municipalityRepository.save(wervik);

		Municipality heuvelland = new Municipality("Heuvelland", 50.74202561049137, 2.825164761088977);
		Set<PostCode> heuvellandPostalCodes = new HashSet<>();
		PostCode postalCode8950 = new PostCode(8950);
		heuvellandPostalCodes.add(postalCode8950);
		PostCode postalCode8951 = new PostCode(8951);
		heuvellandPostalCodes.add(postalCode8951);
		PostCode postalCode8952 = new PostCode(8952);
		heuvellandPostalCodes.add(postalCode8952);
		PostCode postalCode8953 = new PostCode(8953);
		heuvellandPostalCodes.add(postalCode8953);
		PostCode postalCode8954 = new PostCode(8954);
		heuvellandPostalCodes.add(postalCode8954);
		PostCode postalCode8956 = new PostCode(8956);
		heuvellandPostalCodes.add(postalCode8956);
		PostCode postalCode8958 = new PostCode(8958);
		heuvellandPostalCodes.add(postalCode8958);
		heuvelland.setPostcodes(heuvellandPostalCodes);
		municipalityRepository.save(heuvelland);

		Municipality messines = new Municipality("Messines", 50.764423606889764, 2.897705699606299);
		Set<PostCode> messinesPostalCodes = new HashSet<>();
		PostCode postalCode8957 = new PostCode(8957);
		messinesPostalCodes.add(postalCode8957);
		messines.setPostcodes(messinesPostalCodes);
		municipalityRepository.save(messines);

		Municipality poperinge = new Municipality("Poperinge", 50.8543461025641, 2.725568718250377);
		Set<PostCode> poperingePostalCodes = new HashSet<>();
		PostCode postalCode8970 = new PostCode(8970);
		poperingePostalCodes.add(postalCode8970);
		PostCode postalCode8972 = new PostCode(8972);
		poperingePostalCodes.add(postalCode8972);
		PostCode postalCode8978 = new PostCode(8978);
		poperingePostalCodes.add(postalCode8978);
		poperinge.setPostcodes(poperingePostalCodes);
		municipalityRepository.save(poperinge);

		Municipality zonnebeke = new Municipality("Zonnebeke", 50.84641249609375, 2.99648895390625);
		Set<PostCode> zonnebekePostalCodes = new HashSet<>();
		PostCode postalCode8980 = new PostCode(8980);
		zonnebekePostalCodes.add(postalCode8980);
		zonnebeke.setPostcodes(zonnebekePostalCodes);
		municipalityRepository.save(zonnebeke);
	}
}
