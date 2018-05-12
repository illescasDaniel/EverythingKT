#!/bin/bash
kotlinc-js -output output/Kotlin2jsOutput_END.js -meta-info source/endScriptsSource/*.kt -meta-info source/CommonSources/*
kotlinc-js -output output/Kotlin2jsOutput_HEAD.js -meta-info source/headScriptsSource/*.kt -meta-info source/CommonSources/*
kotlinc-js -output output/Kotlin2jsOutput_START.js -meta-info source/startScriptsSource/*.kt -meta-info source/CommonSources/*