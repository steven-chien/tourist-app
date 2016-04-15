Hotspot = new Mongo.Collection('hotspot');
Hotspot.remove({});

var hotspot = [
{
"id": 1,
"name": "dolor",
"region": "Hong Kong",
"coordinate": {
"lat": 22.293678,
"lon": 114.16717

},
"description": "Minim culpa laboris sunt occaecat qui. Sit officia consequat proident culpa voluptate ad. Commodo ipsum duis ad occaecat duis sint et magna mollit ex pariatur cupidatat non reprehenderit. Eu ut mollit in fugiat cupidatat ex labore excepteur sint. Sint labore eiusmod consequat ipsum ex pariatur. Fugiat eiusmod culpa ullamco tempor irure nulla proident ut quis. Sit ullamco reprehenderit incididunt velit tempor.\r\n",
"preview": "https://lh6.googleusercontent.com/proxy/KD3RAGVKSjs4SBrIh6Hb_p8BxLNiqF7JW79Igomf8kZFvlbRKx2XgAxEs3cgBT3VDUhwZ4Bkl-36Han154idrqu0J8OD8Q=w408-h306"

},
{
"id": 2,
"name": "adipisicing",
"region": "Hong Kong",
"coordinate": {
"lat": 22.293678,
"lon": 114.16717

},
"description": "Qui pariatur Lorem exercitation mollit anim eiusmod consequat aliquip ipsum anim do minim veniam. Culpa aute ex commodo culpa consequat reprehenderit proident cillum. Deserunt exercitation adipisicing magna ipsum ullamco sunt labore cupidatat velit ut velit dolore enim voluptate. Qui velit ullamco dolor quis. Aliqua consectetur ut ex nostrud proident labore laboris consequat et.\r\n",
"preview": "https://lh6.googleusercontent.com/proxy/KD3RAGVKSjs4SBrIh6Hb_p8BxLNiqF7JW79Igomf8kZFvlbRKx2XgAxEs3cgBT3VDUhwZ4Bkl-36Han154idrqu0J8OD8Q=w408-h306"

},
{
"id": 3,
"name": "elit",
"region": "Kowloon",
"coordinate": {
"lat": 22.293678,
"lon": 114.16717

},
"description": "Velit occaecat labore ea occaecat dolor Lorem. Occaecat consequat mollit eu ex magna proident tempor anim consectetur anim magna. Labore officia commodo proident ea ex et in sit Lorem esse deserunt cupidatat. Commodo labore veniam magna velit. Ea ad sint nostrud sit exercitation eiusmod reprehenderit veniam consequat magna officia. Magna laborum laboris labore cupidatat sunt in incididunt. Adipisicing ullamco elit eu ullamco culpa deserunt eiusmod irure mollit irure.\r\n",
"preview": "https://lh6.googleusercontent.com/proxy/KD3RAGVKSjs4SBrIh6Hb_p8BxLNiqF7JW79Igomf8kZFvlbRKx2XgAxEs3cgBT3VDUhwZ4Bkl-36Han154idrqu0J8OD8Q=w408-h306"

},
{
"id": 4,
"name": "adipisicing",
"region": "Hong Kong",
"coordinate": {
"lat": 22.293678,
"lon": 114.16717

},
"description": "Cillum quis quis do ipsum mollit. Commodo aliquip reprehenderit labore reprehenderit irure dolor deserunt aliqua ullamco consectetur deserunt commodo velit. Commodo ad sit duis tempor ipsum amet Lorem irure.\r\n",
"preview": "https://lh6.googleusercontent.com/proxy/KD3RAGVKSjs4SBrIh6Hb_p8BxLNiqF7JW79Igomf8kZFvlbRKx2XgAxEs3cgBT3VDUhwZ4Bkl-36Han154idrqu0J8OD8Q=w408-h306"

},
{
"id": 5,
"name": "incididunt",
"region": "Hong Kong",
"coordinate": {
"lat": 22.293678,
"lon": 114.16717

},
"description": "In exercitation dolor eiusmod et elit nisi officia dolor do voluptate sit mollit quis. Ad nulla amet qui excepteur duis qui aute culpa sit. Tempor voluptate duis consequat aliquip labore. Lorem aute aliquip sit labore esse. Cupidatat magna eiusmod aliqua velit anim. Ad aliquip eu ea cillum fugiat sint.\r\n",
"preview": "https://lh6.googleusercontent.com/proxy/KD3RAGVKSjs4SBrIh6Hb_p8BxLNiqF7JW79Igomf8kZFvlbRKx2XgAxEs3cgBT3VDUhwZ4Bkl-36Han154idrqu0J8OD8Q=w408-h306"

}
];

_.each(hotspot, function(doc) {
	Hotspot.insert(doc);

});

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
"id": 0,
"name": "A Symphony of Lights",
"start": Date.parse("2016-04-22 20:00"),
"end": Date.parse("2016-04-22 21:00"),
"location": "Avenue of Star",
"description": "largest light shows in the world."
},
{
"id": 1,
"name": "ea",
"start": Date.parse("2016-04-12 04:39"),
"end": Date.parse("2016-04-20 01:35"),
"location": "Bentonville",
"description": "Sunt non proident ullamco sunt anim ad consectetur ut nostrud reprehenderit. Do eu excepteur laborum et est anim elit aute culpa aute minim. Ut ex voluptate tempor ipsum commodo aliquip ullamco cillum. Id nisi commodo est tempor voluptate laborum ut et nostrud qui non ea. Adipisicing in eu excepteur id ea.\r\n"

},
{
"id": 2,
"name": "culpa",
"start": Date.parse("2016-04-06 06:07"),
"end": Date.parse("2016-04-17 09:59"),
"location": "Kohatk",
"description": "Deserunt deserunt qui minim proident mollit esse laboris laboris sunt. Nulla pariatur proident non duis id aliqua laboris. Cillum Lorem culpa non reprehenderit in cupidatat tempor quis. Fugiat excepteur amet ad ut minim. Veniam esse excepteur aute cillum duis id ad voluptate. Duis officia officia dolore aliquip culpa tempor.\r\n"

},
{
"id": 3,
"name": "reprehenderit",
"start": Date.parse("2016-04-07 02:13"),
"end": Date.parse("2016-04-19 11:43"),
"location": "Moraida",
"description": "Aute laborum sunt minim cupidatat. Labore sunt commodo laboris proident qui cillum. Laborum officia nostrud ex pariatur ea. Sint dolor sint reprehenderit excepteur laboris velit aliquip pariatur consequat id incididunt sint ut pariatur.\r\n"

},
{
"id": 4,
"name": "officia",
"start": Date.parse("2016-04-02 10:20"),
"end": Date.parse("2016-04-21 01:38"),
"location": "Brooktrails",
"description": "Eiusmod duis esse commodo ea pariatur cupidatat. Nostrud occaecat fugiat exercitation culpa elit adipisicing. Aute consequat aliqua sint velit labore velit. Veniam occaecat labore quis aute laborum velit deserunt quis in est ipsum non ipsum commodo. Occaecat reprehenderit aute laboris est aliquip id qui sint cupidatat est. Lorem aute aute ullamco non sit exercitation sint cupidatat dolore dolor et. Sunt consectetur sint duis consectetur laborum Lorem tempor pariatur labore ea quis aliqua.\r\n"

},
{
"id": 5,
"name": "nulla",
"start": Date.parse("2016-04-01 10:04"),
"end": Date.parse("2016-04-26 11:03"),
"location": "Summerset",
"description": "Id id est nulla aute velit. Qui ad quis sint culpa ut sit laborum enim. Qui ea veniam enim aute dolore dolor reprehenderit sint.\r\n"
}
];

_.each(event_data, function(doc) {
	    Events.insert(doc);
});

Comments = new Mongo.Collection('comments');
