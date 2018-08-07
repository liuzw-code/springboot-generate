(function () {
    var $ax = function (url, success, error, async) {
        this.url = url;
        this.type = "post";
        this.data = {};
        this.success = success;
        this.error = error;
        this.contentType = "application/json";
    };

    $ax.prototype = {
        start: function () {
            var me = this;
            $.ajax({
                type: this.type,
                url: this.url,
                data: JSON.stringify(this.data),
                dataType: "json",
                contentType: this.contentType,
                beforeSend: function (data) {

                },
                success: function (data) {
                    me.success(data);
                },
                error: function (data) {
                    me.error(data);
                }
            });
        },

        setData: function (data) {
            this.data = data;
            return this;
        },

        clear: function () {
            this.data = {};
            return this;
        },
    };

    window.$ax = $ax;

}());