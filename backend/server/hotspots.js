Api.addRoute('hotspot_by_id/:name', { authRequired: false }, {
	get: function () {
		var name = parseInt(this.urlParams.name);
		return Hotspot.find({ id: name }, { fields: { '_id': 0 } }).fetch();
	}
});
