var afr = axios.create({
    baseURL: window.location.origin,
    timeout: 1000,
    method: 'post',
    headers: {"Content-Type": "application/json;charset=UTF-8"}
});
afr.interceptors.request.use(function (config) {
    // Do something before request is sent
    return config;
}, function (error) {
    // Do something with request error
    return Promise.reject(error);
});
afr.interceptors.response.use(function (response) {
    var data=response.data;
    if (data.state != undefined && data.state == 0) {
        console.log(data.message)
        layer.open({
            title: '提示',
            content: data.message,
            resize: false,
            time: 5000
        });
    }
    return response;
}, function (error) {
    layer.open({
        title: '提示',
        content: "错误",
        resize: false,
        time: 5000
    });
    return Promise.reject(error);
});
