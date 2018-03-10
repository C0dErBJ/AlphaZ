#!/usr/bin/env node

"use strict";
var c = require('child_process');
var fs = require('fs');
var path = require('path');
var swaggerCodegnVersion = "2.3.1";
var configFilePath = "/assets/appconfig.json";
var swaggerCodegenPath = __dirname + "/swagger-codegen-cli-2.3.1.jar";
var apiDocUri = "http://localhost:5000/swagger/v1/swagger.json";
var hasConfigFile = false;
var hasJavaEnv = false;

var config;
// var dir = fs.readdirSync((path.dirname(__dirname) + "/util/http-services"));
// if (dir.length > 0) {
//     console.log("swagger已经生成，请清空http-services文件夹以重新生成")
//     process.exit(0)
// }
try {
    var data = fs.readFileSync(path.dirname(__dirname) + configFilePath, 'utf8');
    config = JSON.parse(data);
    hasConfigFile = true;
} catch (e) {
    console.log("在asset目录下没有发现appconfig.json配置文件。");
}
if (hasConfigFile) {
    apiDocUri = config["swaggerApiJsonUrl"];
}

try {
    c.execSync("java -version", {
        stdio: [0, 1, 2]
    });
    hasJavaEnv = true;
} catch (e) {
    console.warn("要使用自动生成swagger api调用代码功能，你需要安装java运行环境，并配置环境变量。")
}
if (hasJavaEnv && hasConfigFile) {
    var coreCmd = "java -jar " + swaggerCodegenPath + " generate -i " + apiDocUri + " -l typescript-angular  -o " + path.dirname(__dirname) + "/utility/http-services";
    try {
        c.execSync(coreCmd, {
            stdio: [0, 1, 2]
        });
    } catch (e) {
        console.error("swagger api生成失败，请确认配置正确，api服务已经开启")
    }

}