// Maps to: /api/geo/:coordinate/:nature/:radius
Api.addRoute('geo/:coordinate/:nature/:radius', {authRequired: false}, {
	get: function () {
		var coordinate = this.urlParams.coordinate;
		var nature = this.urlParams.nature;
		var radius = this.urlParams.radius;

		var request = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+coordinate+"&radius="+radius+"&types=food&name="+nature+"&key=AIzaSyCVMAepwI3PM5kCBZacLUuC9rmOkH8VduI";
		var response = HTTP.get(request, {});

		var result = [];
		response.data.results.forEach(function(data) {
			var img = null;
			if(data.photos) {
				var img_path_request = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference="+data.photos[0].photo_reference+"&key=AIzaSyCVMAepwI3PM5kCBZacLUuC9rmOkH8VduI";
				var img_path_response = HTTP.get(img_path_request, { followRedirects: false });
				if(img_path_response.statusCode==302) {
					img = img_path_response.headers.location;
				}
			}
			var obj = { coordinate: data.geometry.location, name: data.name, address: data.vicinity, preview: img }
			result.push(obj);
		});
		return result;
	}
});
