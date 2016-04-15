Hotspot = new Mongo.Collection('hotspot');
Hotspot.remove({});

Hotspot.insert({ id: 6, name: "The Peak", region: "Hong Kong", coordinate: { lat: 22.2758829, lon: 114.1367558 }, description: "With some seven million visitors every year, the Peak is a major tourist attraction of Hong Kong.[3] It offers spectacular views of the city and its harbours. The viewing deck also has coin operated telescopes that the visitors can use to enjoy the cityscape. The number of visitors led to the construction of two major leisure and shopping centres, the Peak Tower and the Peak Galleria, situated adjacent to each other.", preview: "https://lh6.googleusercontent.com/-ue4q1EUUT50/Vjx-ZX7rfCI/AAAAAAAAEW0/72dGyW7kIf4/s408-k-no/" });
Hotspot.insert({ id: 7, name: "Ocean Park", region: "Hong Kong", coordinate: { lat: 22.2466607, lon: 114.1735299 }, description: "Opened in 1977, Ocean Park Hong Kong is a marine-life theme park featuring animal exhibits, thrill rides and shows. In 2012, its impressive ability to offer guests a world-class experience that blends entertainment with education and conservation was confirmed when it became the first Asian winner of the biannual Applause Award, the most prestigious award in the amusement and theme park industry.", preview: "http://www.discoverhongkong.com/eng/images/see-do/highlight-attractions/large/1.1.1.7-Ocean-Park_03b.jpg" });
Hotspot.insert({ id: 8, name: "LegCo Building", region: "Hong Kong", coordinate: { lat: 22.2809605, lon: 114.1581015 }, description: "The Hong Kong government is dedicated to knocking down just about anything that’s more than 20 years old. LegCo has survived because it’s where the government sat for much of the last hundred years and is now the Court of Final Appeal. The building is in grand British colonial style with sturdy granite columns and gilded verandas; a powerful statement by the men in mustaches that once ran the city.", preview: "https://lh6.googleusercontent.com/proxy/IMq5frdYPLnuug3BnVrSx5W9utgcb_s2Uqw_XfvZjNhEbqOemBNTDI_IivU9LYfCiLed1YHS7yqVEz3O13WzRzXETrtPwPgZGXplmcsHOmsQyXdWpdaP6d_m3qttOB7KZi9XMlWa6YSSVCjGX21hlB7qaaEE1w=w408-h271" });
Hotspot.insert({ id: 9, name: "Clock Tower", region: "Kowloon", coordinate: { lat: 22.293678, lon: 114.16717 }, description: "Standing 44-metres tall, the old Clock Tower was erected in 1915 as part of the Kowloon–Canton Railway terminus. The once-bustling station is long gone, but this red brick and granite tower, now preserved as a Declared Monument, survives as an elegant reminder of the Age of Steam.", preview: "http://www.discoverhongkong.com/eng/images/see-do/highlight-attractions/large/1.1.1.4-Clock-Tower_03.jpg" });
Hotspot.insert({ id: 10, name: "Tsim Sha Tsui Promenade", region: "Kowloon", coordinate: { lat: 22.2973554, lon: 114.1773443 }, description: "Starting at the colonial-era Clock Tower and stretching all the way to Hung Hom, a stroll along the Tsim Sha Tsui Promenade takes one past the Hong Kong Cultural Centre and the Hong Kong Space Museum.", preview: "http://www.discoverhongkong.com/eng/images/see-do/highlight-attractions/large/1.1.1.6-Tsim-Sha-Tsui-Promende_03.jpg" });
Hotspot.insert({ id: 11, name: "Chang Chau", region: "New Territories", coordinate: { lat: 22.2095327, lon: 114.0204475 }, description: "Cheung Chau is an island 10 kilometres southwest of Hong Kong Island. It is nicknamed the 'dumbbell island' due to its shape.", preview: "https://lh6.googleusercontent.com/proxy/KD3RAGVKSjs4SBrIh6Hb_p8BxLNiqF7JW79Igomf8kZFvlbRKx2XgAxEs3cgBT3VDUhwZ4Bkl-36Han154idrqu0J8OD8Q=w408-h306" });

Promotion = new Mongo.Collection('promotion');
Promotion.remove({});
Promotion.insert({ name: "History Museum", discount: "less $10 for entrance fee", terms: "3423747164" });
Promotion.insert({ name: "Science Museum", discount: "less $5 for entrance fee", terms: "3423747164" });
Promotion.insert({ name: "Star Ferry Tour", discount: "10% off", terms: "3423747164" });

Events = new Mongo.Collection('events');
Events.remove({});

var event_data = [
{
"id": 999,
"name": "A Symphony of Lights",
"start": Date.parse("2016-04-22 20:00"),
"end": Date.parse("2016-04-22 21:00"),
"location": "Tsim Sha Tsui Promenade",
"description": "largest light shows in the world."
},

  {
    "id": 1,
    "name": "officia",
    "location": "The Peak",
    "description": "Officia aute consectetur velit incididunt anim adipisicing pariatur occaecat. Elit eu laboris cupidatat eiusmod veniam commodo proident do culpa proident. Irure sit et tempor proident. Dolor et ipsum mollit dolore amet eiusmod cupidatat ex in tempor nulla elit quis. Voluptate mollit deserunt laboris tempor. Irure consectetur reprehenderit deserunt Lorem velit adipisicing ipsum. Esse magna culpa sunt duis incididunt dolor aliquip qui nostrud ea occaecat incididunt ea est.\r\n",
    "start_str": "2016-04-03 11:48",
    "end_str": "2016-04-25 06:24",
    "start": 1459655280000,
    "end": 1461536640000
  },
  {
    "id": 2,
    "name": "nisi",
    "location": "The Peak",
    "description": "Minim ad laborum pariatur cillum incididunt ea. Ut pariatur amet sint exercitation veniam Lorem sint. Excepteur ad ad id proident occaecat do nulla amet esse laboris. Enim ipsum quis eiusmod sit eu nostrud adipisicing eiusmod laborum do incididunt est. Exercitation veniam esse eiusmod est in nostrud consectetur. Labore laboris laborum adipisicing pariatur sint velit incididunt ullamco.\r\n",
    "start_str": "2016-04-01 12:03",
    "end_str": "2016-04-22 11:22",
    "start": 1459483380000,
    "end": 1461295320000
  },
  {
    "id": 3,
    "name": "veniam",
    "location": "The Peak",
    "description": "Dolor in magna et pariatur ut do. Et in nulla consectetur sunt et irure mollit culpa elit ex eiusmod fugiat. Proident deserunt ut laboris esse veniam dolore elit id. Aliqua est nisi irure consequat.\r\n",
    "start_str": "2016-04-11 04:21",
    "end_str": "2016-04-17 03:21",
    "start": 1460319660000,
    "end": 1460834460000
  },
  {
    "id": 4,
    "name": "veniam",
    "location": "The Peak",
    "description": "Minim labore labore est aliquip labore sunt. Laboris enim velit anim quis dolor culpa occaecat est. Exercitation in aute eiusmod reprehenderit in pariatur ex sit aliqua voluptate consequat dolore ullamco sunt.\r\n",
    "start_str": "2016-04-11 10:06",
    "end_str": "2016-04-26 04:01",
    "start": 1460340360000,
    "end": 1461614460000
  },
  {
    "id": 5,
    "name": "minim",
    "location": "The Peak",
    "description": "Ut qui ullamco minim est minim in aliqua ex aute pariatur aliqua. In velit exercitation voluptate in irure. Veniam laboris irure magna nostrud tempor reprehenderit nostrud eiusmod culpa voluptate elit. Labore labore aute elit anim deserunt. In nulla irure esse laboris culpa labore consectetur reprehenderit nisi eiusmod ad commodo voluptate. Deserunt duis fugiat pariatur nulla excepteur exercitation aliquip culpa irure elit aliquip.\r\n",
    "start_str": "2016-04-12 05:51",
    "end_str": "2016-04-26 05:36",
    "start": 1460411460000,
    "end": 1461620160000
  },
  {
    "id": 6,
    "name": "id",
    "location": "Clock Tower",
    "description": "Ex in quis reprehenderit ad veniam. Minim commodo magna deserunt sunt elit consequat laboris sunt occaecat officia exercitation sunt. Proident pariatur labore ea Lorem.\r\n",
    "start_str": "2016-04-10 11:20",
    "end_str": "2016-04-29 08:19",
    "start": 1460258400000,
    "end": 1461889140000
  },
  {
    "id": 7,
    "name": "elit",
    "location": "Clock Tower",
    "description": "Lorem deserunt ut irure aute excepteur commodo id exercitation veniam. Non minim anim sint dolor ex magna ipsum. Irure fugiat adipisicing proident adipisicing ullamco aliqua ad dolor duis ad cillum. Mollit qui veniam duis adipisicing anim mollit ea aliquip eu commodo cillum qui quis excepteur.\r\n",
    "start_str": "2016-04-01 11:39",
    "end_str": "2016-04-18 04:15",
    "start": 1459481940000,
    "end": 1460924100000
  },
  {
    "id": 8,
    "name": "voluptate",
    "location": "Clock Tower",
    "description": "Dolor ut fugiat id non proident ex in et ipsum dolore proident. Deserunt culpa cillum consectetur est in laborum non. Ad ullamco Lorem anim et. Ullamco proident eu labore occaecat occaecat in adipisicing deserunt Lorem in fugiat proident mollit.\r\n",
    "start_str": "2016-04-01 05:36",
    "end_str": "2016-04-22 08:54",
    "start": 1459460160000,
    "end": 1461286440000
  },
  {
    "id": 9,
    "name": "laboris",
    "location": "Clock Tower",
    "description": "Cillum qui esse nostrud mollit. Aliquip ad esse deserunt non eu et anim qui minim elit cillum nostrud. Nulla id labore ut duis et. Dolore veniam consequat officia tempor aute esse ex. Dolor id ea ex qui. Cillum est Lorem ea Lorem ad sunt sunt magna elit in Lorem deserunt.\r\n",
    "start_str": "2016-04-09 02:29",
    "end_str": "2016-04-23 10:31",
    "start": 1460140140000,
    "end": 1461378660000
  },
  {
    "id": 10,
    "name": "commodo",
    "location": "Clock Tower",
    "description": "Duis proident dolor ut aliquip culpa est ex labore occaecat magna dolor ad. Consectetur culpa enim ea tempor eu aliquip duis. Fugiat proident enim deserunt esse quis nisi laboris nisi.\r\n",
    "start_str": "2016-04-01 08:52",
    "end_str": "2016-04-23 07:04",
    "start": 1459471920000,
    "end": 1461366240000
  },
  {
    "id": 11,
    "name": "exercitation",
    "location": "Tsim Sha Tsui Promenade",
    "description": "Consequat qui ad eiusmod quis ad cillum. Ea excepteur enim id tempor culpa commodo ut enim ad laboris adipisicing aliqua pariatur voluptate. Cillum est est dolor in proident occaecat et proident. Do tempor id culpa fugiat dolor ut culpa do dolore labore fugiat. Dolore officia adipisicing magna duis velit. Irure tempor occaecat veniam culpa Lorem veniam cupidatat ut.\r\n",
    "start_str": "2016-04-01 04:21",
    "end_str": "2016-04-27 03:52",
    "start": 1459455660000,
    "end": 1461700320000
  },
  {
    "id": 12,
    "name": "nisi",
    "location": "Tsim Sha Tsui Promenade",
    "description": "Reprehenderit ea in incididunt esse eu est reprehenderit duis cupidatat ad do. Dolor aliquip tempor ut mollit qui deserunt laborum et irure. Anim deserunt reprehenderit cillum et ad qui labore proident sit. Labore velit irure dolor do nisi aute incididunt ea. Ut proident esse elit Lorem exercitation velit. Ad pariatur magna veniam duis velit officia laborum deserunt in ex magna eiusmod irure.\r\n",
    "start_str": "2016-04-11 04:17",
    "end_str": "2016-04-25 02:28",
    "start": 1460319420000,
    "end": 1461522480000
  },
  {
    "id": 13,
    "name": "proident",
    "location": "Tsim Sha Tsui Promenade",
    "description": "Ad sint duis incididunt amet. Ad dolore id ex nisi nisi velit velit velit eiusmod aute excepteur. Labore sunt consectetur aliquip exercitation tempor elit laboris laboris id. Ipsum dolor enim id et. Tempor laborum ad elit cillum quis magna commodo dolor eu incididunt velit fugiat.\r\n",
    "start_str": "2016-04-04 11:21",
    "end_str": "2016-04-26 07:43",
    "start": 1459740060000,
    "end": 1461627780000
  },
  {
    "id": 14,
    "name": "fugiat",
    "location": "Tsim Sha Tsui Promenade",
    "description": "Amet Lorem ea dolor sunt ea tempor. Incididunt id sit veniam ad ullamco est sit ad fugiat. Cupidatat aliqua qui quis excepteur officia culpa occaecat ipsum est. Elit excepteur deserunt excepteur Lorem adipisicing quis ullamco labore exercitation id sunt fugiat. Aliqua sint laborum excepteur minim culpa do cupidatat anim. Aliquip sit reprehenderit nisi sit ullamco cillum qui enim dolor eu nostrud.\r\n",
    "start_str": "2016-04-09 07:03",
    "end_str": "2016-04-26 06:44",
    "start": 1460156580000,
    "end": 1461624240000
  },
  {
    "id": 15,
    "name": "aute",
    "location": "Tsim Sha Tsui Promenade",
    "description": "Laboris dolore incididunt anim velit sint cillum duis excepteur laborum proident ad sint aute voluptate. Cupidatat in mollit eiusmod excepteur exercitation velit sunt nostrud ea amet ipsum nisi sit. Ullamco occaecat consequat duis nostrud. Fugiat qui sit non enim laborum veniam. Qui commodo ex pariatur commodo Lorem et incididunt nisi.\r\n",
    "start_str": "2016-04-08 03:53",
    "end_str": "2016-04-22 04:35",
    "start": 1460058780000,
    "end": 1461270900000
  },
{
    "id": 16,
    "name": "minim",
    "location": "Chang Chau",
    "description": "Mollit velit proident Lorem sunt qui eiusmod incididunt cillum non excepteur ad pariatur. Sint consectetur amet sunt veniam. Amet dolor adipisicing sunt quis laborum sint occaecat elit nulla. Commodo Lorem mollit pariatur ut exercitation voluptate in est irure non.\r\n",
    "start_str": "2016-04-01 05:45",
    "end_str": "2016-04-20 09:00",
    "start": 1459460700000,
    "end": 1461114000000
  },
  {
    "id": 17,
    "name": "consequat",
    "location": "Chang Chau",
    "description": "Labore adipisicing labore reprehenderit ea consectetur duis cupidatat amet qui nulla aute. Commodo in laboris mollit duis cillum. Culpa in consequat eu anim labore aliqua officia. Proident cupidatat nostrud laborum aliqua nulla ex laborum ipsum. Amet sint est ullamco magna deserunt elit mollit. Voluptate non adipisicing aliqua laboris fugiat tempor ad cillum commodo laboris reprehenderit exercitation.\r\n",
    "start_str": "2016-04-13 05:27",
    "end_str": "2016-04-24 08:07",
    "start": 1460496420000,
    "end": 1461456420000
  },
  {
    "id": 18,
    "name": "mollit",
    "location": "Chang Chau",
    "description": "Enim ad excepteur occaecat et incididunt laboris ex laboris voluptate. Consequat duis nulla dolor dolore elit veniam minim. Sunt sunt exercitation ullamco ad dolore officia et Lorem. Exercitation nulla dolore adipisicing dolore exercitation nisi anim nulla. Ad id eiusmod culpa ut cillum adipisicing est ex. Commodo ea aliqua Lorem consectetur in magna labore sit proident non aliquip. Fugiat sit reprehenderit reprehenderit culpa commodo commodo enim commodo elit nulla eiusmod.\r\n",
    "start_str": "2016-04-06 10:41",
    "end_str": "2016-04-25 03:47",
    "start": 1459910460000,
    "end": 1461527220000
  },
  {
    "id": 19,
    "name": "sint",
    "location": "Chang Chau",
    "description": "Amet enim ipsum qui ex ipsum nostrud cupidatat aute veniam ullamco aute quis irure. Laboris qui eu magna Lorem adipisicing. In est esse ad labore qui commodo duis. Ea mollit Lorem laborum cupidatat occaecat labore elit laboris laborum sint quis.\r\n",
    "start_str": "2016-04-13 12:45",
    "end_str": "2016-04-16 06:30",
    "start": 1460522700000,
    "end": 1460759400000
  },
  {
    "id": 20,
    "name": "pariatur",
    "location": "Chang Chau",
    "description": "Ad tempor voluptate sunt commodo eiusmod ipsum magna. Id consequat proident sunt do velit mollit enim laboris mollit excepteur. Cupidatat sint enim ex ut non sint laboris ut. Elit nulla veniam officia ullamco magna dolore proident mollit nostrud sint pariatur elit ad. Consectetur excepteur aliqua proident sint consectetur aliquip elit ad ad. Cillum ea duis minim irure laboris do cupidatat dolore do consectetur amet.\r\n",
    "start_str": "2016-04-02 08:54",
    "end_str": "2016-04-29 05:49",
    "start": 1459558440000,
    "end": 1461880140000
  }
];

_.each(event_data, function(doc) {
	    Events.insert(doc);
});

Comments = new Mongo.Collection('comments');
