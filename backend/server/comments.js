// Maps to: /api/comments/:name/:comment
Api.addRoute('commenting/:name/:comment', {authRequired: false}, {
	get: function () {
		var name = this.urlParams.name;
		var comment = this.urlParams.comment;
		Comments.insert({ name: name, comment: comment });
		return { name: name, comment: comment };
	}
});

// Maps to: /api/getcomments/:name/:comment
Api.addRoute('comments/:name', {authRequired: false}, {
	get: function () {
		var name = this.urlParams.name;
		return Comments.find({ name: name }, { fields: { 'name': 0, '_id': 0 } }).fetch();
	}
});
