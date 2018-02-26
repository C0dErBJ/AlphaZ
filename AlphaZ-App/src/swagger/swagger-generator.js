#!/usr/bin/env node

"use strict";
var c = require('child_process');
var fs = require('fs');
var path = require('path');
var swaggerCodegnVersion = "2.3.1";
var configFilePath = "/assets/appconfig.json";
var swaggerCodegenPath = __dirname + "/swagger-codegen-cli-2.3.1.jar";
var apiDocUri = "http://localhost:5000/swagger/v1/swagger.json";
var outputPath = "~/downloads/tt";
var hasConfigFile = false;
var hasJavaEnv = false;

var config;
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
    console.log("要使用自动生成swagger api调用代码功能，你需要安装java运行环境，并配置环境变量。")
}
if (hasJavaEnv && hasConfigFile) {
    var coreCmd = "java -jar " + swaggerCodegenPath + " generate -i " + apiDocUri + " -l typescript-angular  -o " + outputPath;
    c.execSync(coreCmd, {
        stdio: [0, 1, 2]
    });
}