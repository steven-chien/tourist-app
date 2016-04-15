Api.addRoute('events_by_spot/:name', { authRequired: false }, {
	get: function () {
		var name = this.urlParams.name;
		return Events.find({ location: name }, { fields: { '_id': 0 } }).fetch();
	}
});

Api.addRoute('events_by_id/:name', { authRequired: false }, {
	get: function () {
		var name = this.urlParams.name;
		return Events.find({ id: name }, { fields: { '_id': 0 } }).fetch();
	}
});
