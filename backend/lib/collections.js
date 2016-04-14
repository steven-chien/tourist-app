Hotspot = new Mongo.Collection('hotspot');
Hotspot.remove({});
Hotspot.insert({ name: "The Peak", region: "Hong Kong", coordinate: { lat: 22.2758829, lon: 114.1367558 }, description: "With some seven million visitors every year, the Peak is a major tourist attraction of Hong Kong.[3] It offers spectacular views of the city and its harbours. The viewing deck also has coin operated telescopes that the visitors can use to enjoy the cityscape. The number of visitors led to the construction of two major leisure and shopping centres, the Peak Tower and the Peak Galleria, situated adjacent to each other.", preview: "https://lh6.googleusercontent.com/-ue4q1EUUT50/Vjx-ZX7rfCI/AAAAAAAAEW0/72dGyW7kIf4/s408-k-no/" });
Hotspot.insert({ name: "Ocean Park", region: "Hong Kong", coordinate: { lat: 22.2466607, lon: 114.1735299 }, description: "Opened in 1977, Ocean Park Hong Kong is a marine-life theme park featuring animal exhibits, thrill rides and shows. In 2012, its impressive ability to offer guests a world-class experience that blends entertainment with education and conservation was confirmed when it became the first Asian winner of the biannual Applause Award, the most prestigious award in the amusement and theme park industry.", preview: "http://www.discoverhongkong.com/eng/images/see-do/highlight-attractions/large/1.1.1.7-Ocean-Park_03b.jpg" });
Hotspot.insert({ name: "LegCo Building", region: "Hong Kong", coordinate: { lat: 22.2809605, lon: 114.1581015 }, description: "The Hong Kong government is dedicated to knocking down just about anything that’s more than 20 years old. LegCo has survived because it’s where the government sat for much of the last hundred years and is now the Court of Final Appeal. The building is in grand British colonial style with sturdy granite columns and gilded verandas; a powerful statement by the men in mustaches that once ran the city.", preview: "https://lh6.googleusercontent.com/proxy/IMq5frdYPLnuug3BnVrSx5W9utgcb_s2Uqw_XfvZjNhEbqOemBNTDI_IivU9LYfCiLed1YHS7yqVEz3O13WzRzXETrtPwPgZGXplmcsHOmsQyXdWpdaP6d_m3qttOB7KZi9XMlWa6YSSVCjGX21hlB7qaaEE1w=w408-h271" });
Hotspot.insert({ name: "Clock Tower", region: "Kowloon", coordinate: { lat: 22.293678, lon: 114.16717 }, description: "Standing 44-metres tall, the old Clock Tower was erected in 1915 as part of the Kowloon–Canton Railway terminus. The once-bustling station is long gone, but this red brick and granite tower, now preserved as a Declared Monument, survives as an elegant reminder of the Age of Steam.", preview: "http://www.discoverhongkong.com/eng/images/see-do/highlight-attractions/large/1.1.1.4-Clock-Tower_03.jpg" });
Hotspot.insert({ name: "Tsim Sha Tsui Promenade", region: "Kowloon", coordinate: { lat: 22.2973554, lon: 114.1773443 }, description: "Starting at the colonial-era Clock Tower and stretching all the way to Hung Hom, a stroll along the Tsim Sha Tsui Promenade takes one past the Hong Kong Cultural Centre and the Hong Kong Space Museum.", preview: "http://www.discoverhongkong.com/eng/images/see-do/highlight-attractions/large/1.1.1.6-Tsim-Sha-Tsui-Promende_03.jpg" });
Hotspot.insert({ name: "Chang Chau", region: "New Territories", coordinate: { lat: 22.2095327, lon: 114.0204475 }, description: "Cheung Chau is an island 10 kilometres southwest of Hong Kong Island. It is nicknamed the 'dumbbell island' due to its shape.", preview: "https://lh6.googleusercontent.com/proxy/KD3RAGVKSjs4SBrIh6Hb_p8BxLNiqF7JW79Igomf8kZFvlbRKx2XgAxEs3cgBT3VDUhwZ4Bkl-36Han154idrqu0J8OD8Q=w408-h306" });

Promotion = new Mongo.Collection('promotion');
Promotion.remove({});
Promotion.insert({ name: "History Museum", discount: "less $10 for entrance fee", terms: "3423747164" });
Promotion.insert({ name: "Science Museum", discount: "less $5 for entrance fee", terms: "3423747164" });
Promotion.insert({ name: "Star Ferry Tour", discount: "10% off", terms: "3423747164" });

Events = new Mongo.Collection('events');
Events.remove({});
//Events.insert({ name: "A Symphony of Lights", spot: 'Tsim Sha Tsui Promenade', start: , description: "The world's largest daily light show." });
//Events.insert({ name: "The Piu Sik  (Floating Colours) Parade", spot: 'Chang Chau', time: "", date: "11-15 May 2016", description: "The highlight of the festival is the Piu Sik (Floating Colours) Parade. This dramatic reenactment of the ceremonial parade held to drive away a plague a century ago sees young children, dressed in the guises of traditional deities and modern celebrities, balance on poles and accompanied by gongs and lion dancers, appearing to float above the crowds in a carnival-like procession." });
//Events.insert({ name: "Dragon Boat Races", spot: '', time: "", date: "9 June 2016", description: "he Dragon Boat Festival, also known as Tuen Ng Festival, commemorates the death of Qu Yuan, a Chinese national hero. In a protest against corrupt rulers, Qu drowned himself in the Mi Lo River. To scare away fish from eating his body, the townspeople beat drums and threw glutinous rice dumplings called 'zong' into the water. Today, this event is remembered by Chinese people around the world, who eat zong and beat drums on dragon boat races." });
//Events.insert({ name: "Hong Kong Wine and Dine Fest", spot: 'Central Harbourfront Event Space', time: "", date: "27–30 October 2016 (Thursday to Sunday)", description: "Around 144,000 people came to the 2015 event to sample great wines and culinary offerings from 23 countries." });

var event_data = [
{
"name": "ea",
"start": Date.parse("2016-04-12 04:39"),
"end": Date.parse("2016-04-20 01:35"),
"location": "Bentonville",
"description": "Sunt non proident ullamco sunt anim ad consectetur ut nostrud reprehenderit. Do eu excepteur laborum et est anim elit aute culpa aute minim. Ut ex voluptate tempor ipsum commodo aliquip ullamco cillum. Id nisi commodo est tempor voluptate laborum ut et nostrud qui non ea. Adipisicing in eu excepteur id ea.\r\n"

},
{
"name": "culpa",
"start": Date.parse("2016-04-06 06:07"),
"end": Date.parse("2016-04-17 09:59"),
"location": "Kohatk",
"description": "Deserunt deserunt qui minim proident mollit esse laboris laboris sunt. Nulla pariatur proident non duis id aliqua laboris. Cillum Lorem culpa non reprehenderit in cupidatat tempor quis. Fugiat excepteur amet ad ut minim. Veniam esse excepteur aute cillum duis id ad voluptate. Duis officia officia dolore aliquip culpa tempor.\r\n"

},
{
"name": "reprehenderit",
"start": Date.parse("2016-04-07 02:13"),
"end": Date.parse("2016-04-19 11:43"),
"location": "Moraida",
"description": "Aute laborum sunt minim cupidatat. Labore sunt commodo laboris proident qui cillum. Laborum officia nostrud ex pariatur ea. Sint dolor sint reprehenderit excepteur laboris velit aliquip pariatur consequat id incididunt sint ut pariatur.\r\n"

},
{
"name": "officia",
"start": Date.parse("2016-04-02 10:20"),
"end": Date.parse("2016-04-21 01:38"),
"location": "Brooktrails",
"description": "Eiusmod duis esse commodo ea pariatur cupidatat. Nostrud occaecat fugiat exercitation culpa elit adipisicing. Aute consequat aliqua sint velit labore velit. Veniam occaecat labore quis aute laborum velit deserunt quis in est ipsum non ipsum commodo. Occaecat reprehenderit aute laboris est aliquip id qui sint cupidatat est. Lorem aute aute ullamco non sit exercitation sint cupidatat dolore dolor et. Sunt consectetur sint duis consectetur laborum Lorem tempor pariatur labore ea quis aliqua.\r\n"

},
{
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
