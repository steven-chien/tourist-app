// Maps to: /api/articles/:id
Api.addRoute('articles/:lat/:lon', {authRequired: false}, {
	get: function () {
		console.log(this.urlParams.lat);
		console.log(this.urlParams.lon);
		return Articles.findOne(this.urlParams.id);
	}
});
