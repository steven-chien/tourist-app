Hotspot = new Mongo.Collection('hotspot');
Hotspot.remove({});
Hotspot.insert({ name: "The Peak", region: "Hong Kong", coordinate: { lat: 22.2758829, lon: 114.1367558 }, description: "With some seven million visitors every year, the Peak is a major tourist attraction of Hong Kong.[3] It offers spectacular views of the city and its harbours. The viewing deck also has coin operated telescopes that the visitors can use to enjoy the cityscape. The number of visitors led to the construction of two major leisure and shopping centres, the Peak Tower and the Peak Galleria, situated adjacent to each other.", preview: "https://lh6.googleusercontent.com/-ue4q1EUUT50/Vjx-ZX7rfCI/AAAAAAAAEW0/72dGyW7kIf4/s408-k-no/" });
Hotspot.insert({ name: "Ocean Park", region: "Hong Kong", coordinate: { lat: 22.2466607, lon: 114.1735299 }, description: "Opened in 1977, Ocean Park Hong Kong is a marine-life theme park featuring animal exhibits, thrill rides and shows. In 2012, its impressive ability to offer guests a world-class experience that blends entertainment with education and conservation was confirmed when it became the first Asian winner of the biannual Applause Award, the most prestigious award in the amusement and theme park industry.", preview: "http://www.discoverhongkong.com/eng/images/see-do/highlight-attractions/large/1.1.1.7-Ocean-Park_03b.jpg" });
Hotspot.insert({ name: "LegCo Building", region: "Hong Kong", coordinate: { lat: 22.2809605, lon: 114.1581015 }, description: "The Hong Kong government is dedicated to knocking down just about anything that’s more than 20 years old. LegCo has survived because it’s where the government sat for much of the last hundred years and is now the Court of Final Appeal. The building is in grand British colonial style with sturdy granite columns and gilded verandas; a powerful statement by the men in mustaches that once ran the city.", preview: "https://lh6.googleusercontent.com/proxy/IMq5frdYPLnuug3BnVrSx5W9utgcb_s2Uqw_XfvZjNhEbqOemBNTDI_IivU9LYfCiLed1YHS7yqVEz3O13WzRzXETrtPwPgZGXplmcsHOmsQyXdWpdaP6d_m3qttOB7KZi9XMlWa6YSSVCjGX21hlB7qaaEE1w=w408-h271" });
Hotspot.insert({ name: "Clock Tower", region: "Kowloon", coordinate: { lat: 22.293678, lon: 114.16717 }, description: "Standing 44-metres tall, the old Clock Tower was erected in 1915 as part of the Kowloon–Canton Railway terminus. The once-bustling station is long gone, but this red brick and granite tower, now preserved as a Declared Monument, survives as an elegant reminder of the Age of Steam.", preview: "http://www.discoverhongkong.com/eng/images/see-do/highlight-attractions/large/1.1.1.4-Clock-Tower_03.jpg" });
Hotspot.insert({ name: "Tsim Sha Tsui Promenade", region: "Kowloon", coordinate: { lat: 22.2973554, lon: 114.1773443 }, description: "Starting at the colonial-era Clock Tower and stretching all the way to Hung Hom, a stroll along the Tsim Sha Tsui Promenade takes one past the Hong Kong Cultural Centre and the Hong Kong Space Museum.", preview: "http://www.discoverhongkong.com/eng/images/see-do/highlight-attractions/large/1.1.1.6-Tsim-Sha-Tsui-Promende_03.jpg" });

Promotion = new Mongo.Collection('promotion');
Promotion.remove({});
Promotion.insert({ name: "History Museum", discount: "less $10 for entrance fee", terms: "3423747164" });
Promotion.insert({ name: "Science Museum", discount: "less $5 for entrance fee", terms: "3423747164" });
Promotion.insert({ name: "Star Ferry Tour", discount: "10% off", terms: "3423747164" });

Events = new Mongo.Collection('events');
Events.remove({});
Events.insert({ name: "A Symphony of Lights", spot: 'Tsim Sha Tsui Promenade', time: "8pm", date: "Everyday" });

Comments = new Mongo.Collection('comments');
