Api.addRoute('events_by_spot/:name', { authRequired: false }, {
	get: function () {
		var name = this.urlParams.name;
		return Events.find({ location: name }, { fields: { 'name': 0, '_id': 0 } }).fetch();
	}
});
