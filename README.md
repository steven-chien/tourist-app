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
##### Response
JSON array containing all result objects. Each object contains: Coordinate(lat, lon), Name, Address and if any, direct URL to preview picture at Google.
#### Example
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
	    "lng": 151.194293
		
},
    "name": "BLACK by ezard",
	"address": "Harbourside, The Star Level G/ 80 Pyrmont Street, Pyrmont",
	    "preview": "https://lh4.googleusercontent.com/-BK3q0dpSNJw/U_6ejMx0weI/AAAAAAAAAAk/eyK9JcJ8KNg/s1600-w400/"
	      
}, ...
]```
