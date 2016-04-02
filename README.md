# Tourist App
App for COMP437 group project, spring 2016.

## Description
Written with MeteorJS.

#### Dependency
- Google API server key
- Package
	- HTTP: a package for making HTTP requests
	- Restivus: a package for RESTFUL API support
- Deployment
	- NodeJS v0.10.40

#### Usage
##### Restful request
/api/geo/(coordinate)/(nature of place)/(search radius)
/api/hotspot
/api/promotion
/api/commenting/(name)/(comment)
/api/comments/(name)
##### Response
JSON array containing all result objects. Each object contains: Coordinate(lat, lon), Name, Address and if any, direct URL to preview picture at Google.
#### Example: Tunnel Google Place API
Request: https://46.101.253.211/api/geo/-33.8670,151.1957/restaurant/500 results in:
```json
[
{
"coordinate": {
      "lat": -33.8681472,
	    "lng": 151.1946364
		
},
    "name": "Momofuku SeiÅbo",
	"address": "The Star/80 Pyrmont Street, Pyrmont",
	    "preview": "https://lh4.googleusercontent.com/-DpR48KFu-NM/VXqE4kNDS3I/AAAAAAACkcc/IKvmg0eeP3s/s1600-w400/"
	      
},
{
"coordinate": {
      "lat": -33.868105,
	"log": 151.194293
		
},
    "name": "BLACK by ezard",
	"address": "Harbourside, The Star Level G/ 80 Pyrmont Street, Pyrmont",
	    "preview": "https://lh4.googleusercontent.com/-BK3q0dpSNJw/U_6ejMx0weI/AAAAAAAAAAk/eyK9JcJ8KNg/s1600-w400/"
	      
}, ...
]
```

Request: https://46.101.253.211/api/hotspot results in:
```json
{
	"status": "success",
	"data": [
	{
		"name": "The Peak",
		"coordinate": {
			"lat": 22.2758829,
			"lon": 114.1367558
		},
		"description": "With some seven million visitors every year, the Peak is a major tourist attraction of Hong Kong.[3] It offers spectacular views of the city and its harbours. The viewing deck also has coin operated telescopes that the visitors can use to enjoy the cityscape. The number of visitors led to the construction of two major leisure and shopping centres, the Peak Tower and the Peak Galleria, situated adjacent to each other.",
		"preview": "https://lh6.googleusercontent.com/-ue4q1EUUT50/Vjx-ZX7rfCI/AAAAAAAAEW0/72dGyW7kIf4/s408-k-no/",
		"_id": "yfrY4h5rfMHH4mqox"
	}, ...
]
```

Request: https://46.101.253.211/api/promotion
```json
{
	"status": "success",
	"data": [
		{
			"name": "History Museum",
			"discount": "less $10 for entrance fee",
			"terms": "3423747164",
			"_id": "pEgAjWhzoRodctyuQ"
		}, ...
	]
}
```

Request: https://46.101.253.211/api/commenting/The%20Peak/Very%20Good results in:
```json
{
	"name": "The Peak",
	"comment": "Very Good"
}
```

Request: https://46.101.253.211/api/comments/The%20Peak results in:
```json
[
	{
		"comment": "Very Good"
	}
]
```
