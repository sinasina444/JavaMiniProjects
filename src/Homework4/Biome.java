package Homework4;

public enum Biome {
	EQUATORIAL,TROPICAL,SUBTROPICAL,MEDITERRANEAN,WARMTEMPERATE,NEMORAL,CONTINENTAL,BOREAL,POLAR;
	
	static void PrintDescription(String[] args){
		String strOutput;
	    //support both upper and lower character input
	    for(String strType:args){
		     switch(strType){
			case "EQUATORIAL":
			case "equatorial":
			        strOutput = Biome.EQUATORIAL.getDescription();
			        break;
			case "TROPICAL":
			case "tropical":
				strOutput = Biome.TROPICAL.getDescription();
				break;
			case "SUBTROPICAL":
			case "subtropical":
				strOutput = Biome.SUBTROPICAL.getDescription();
				break;
			case "MEDITERRANEAN":
			case "mediterranean":
				strOutput = Biome.MEDITERRANEAN.getDescription();
				break;
			case "WARMTEMPERATE":
			case "warmtemperate":
				strOutput = Biome.WARMTEMPERATE.getDescription();
				break;
			case "NEMORAL":
			case "nemoral":
				strOutput = Biome.NEMORAL.getDescription();
				break;
			case "CONTINENTAL":
			case "continental":
				strOutput = Biome.CONTINENTAL.getDescription();
				break;
			case "BOREAL":
			case "boreal":
				strOutput = Biome.BOREAL.getDescription();
				break;
			case "POLAR":
			case "polar":
				strOutput = Biome.POLAR.getDescription();
				break;
			default:
			        strOutput = "wrong input,please check again!\n";
				break;
			}
		     System.out.println(strOutput);
		}
		//print all the description
		/*try{
			//check whether the input string is illeagal
			for(int i=0;i<len;i++){
				System.out.println(biomes[i].getDescription());
			}
		}catch(Exception e){
			System.out.println("error! recheck the input please,error type:"+e);
		}*/
	}
	
	String getDescription(){
		String result="";
		switch(this){
		case EQUATORIAL:
			result = "Equatorial Description:\n"
					+ "Zonobiome: ZB I. Equatorial, always moist, little temperature seasonality;\n"
					+ "Zonal soil type: Equatorial brown clays;\n"
					+ "Zonal vegetation type:Evergreen tropical rainforest\n";
			break;
		case TROPICAL:
			result = "Tropical Description:\n"
					+ "Zonobiome:ZB II. Tropical, summer rainy season and cooler “winter” dry season;\n"
					+ "Zonal soil type: Red clays or red earths;\n"
					+ "Tropical seasonal forest, seasonal dry forest, scrub, or savanna\n";
			break;
		case SUBTROPICAL:
			result = "tropical Description: \n"
					+ "Zonobiome:  Subtropical, highly seasonal, arid climate;\n"
					+ "Zonal soil type: Serosemes, sierozemes; \n"
					+ "Zonal vegetation type:Desert vegetation with considerable exposed surface\n";
			break;
		case MEDITERRANEAN:
			result = "Mediterranean Description:\n"
					+ "Zonobiome: ZB IV. Mediterranean, winter rainy season and summer drought;\n"
					+ "Zonal soil type: Mediterranean brown earths;\n"
					+ "Zonal vegetation type:Sclerophyllous (drought-adapted), frost-sensitive shrublands and woodlands\n";
			break;
		case WARMTEMPERATE:
			result = "Warmtemperate Description:\n"
					+ "Zonobiome: ZB V. Warm temperate, occasional frost, often with summer rainfall maximum;\n"
					+ "Zonal soil type: Yellow or red forest soils, slightly podsolic soils;\n"
					+ "Zonal vegetation type:Temperate evergreen forest, somewhat frost-sensitive\n";
			break;
		case NEMORAL:
			result = "Nemoral Description:\n"
					+ "Zonobiome: ZB VI. Nemoral, moderate climate with winter freezing;\n"
					+ "Zonal soil type: Forest brown earths and grey forest soils;\n"
					+ "Zonal vegetation type:Frost-resistant, deciduous, temperate forest\n";
			break;
		case CONTINENTAL:
			result = "Equatorial Description:\n"
					+ "Zonobiome: ZB VII. Continental, arid, with warm or hot summers and cold winters\n"
					+ "Zonal soil type: Chernozems to serozems\n"
					+ "Zonal vegetation type:Grasslands and temperate deserts\n";
			break;
		case BOREAL:
			result = "Boreal Description:\n"
					+ "Zonobiome: ZB VIII. Boreal, cold temperate with cool summers and long winters\n"
					+ "Zonal soil type: Podsols;\n"
					+ "Zonal vegetation type:Evergreen, frost-hardy, needle-leaved forest (taiga)\n";
			break;
		case POLAR:
			result = "Polar Description:\n"
					+ "Zonobiome: ZB IX. Polar, short, cool summers and long, cold winters\n"
					+ "Zonal soil type: Tundra humus soils with solifluction (permafrost soils)\n"
					+ "Zonal vegetation type:Low, evergreen vegetation, without trees, growing over permanently frozen soils\n";
			break;
		default:
			result = "Description not found in this type!\n";
		}
		return result;
	}
}
