# WeBookmark

[![Build Status](https://travis-ci.org/drcabral/WeBookmark.svg?branch=master)](https://travis-ci.org/drcabral/WeBookmark)

**(This is a work in progress!)**

As a book lover and passionate about Android development and the trends in platform, I created this project to make both of my interests together.

WeBookmark is an app that allows you to keep tracking the books that you're reading and your progress.

**(List of functionalities will be added soon!)**

More than this, this project contains what I'm calling **modern Android MVVM codebase template**.
You can find this basic project in release <span style="color:red">(**Will be released soon!**)</span>

## WeBookmark Modern Android MVVM Codebase Template Stack

The idea here is provide a template for every Android project, from a simple one to a complex, because of its modern components, separation of concepts, SOLID adequation and code quality assured of unit tests.

### Architecture

Here is the diagram of the used MVVM architecture:

<p align="center">
  <img src="https://doc-14-5g-docs.googleusercontent.com/docs/securesc/ha0ro937gcuc7l7deffksulhg5h7mbp1/bt62jcb2abbi26tsav8hinoqm9lrn3u7/1580220000000/16443675139245670547/*/18p-JTXaTcErl3YbQ-kMMDIInviYhJ-q0?e=view">
</p>

There are some key points here for some architectural decisions. For example, i know that for a small project maybe create more classes and code just to apply interfaces between the main repository and the local/remote data access can be more than we really need. But, the idea here is to apply concepts to make easy to expand the project to a large and complex one. Keep it in mind when you are reading the code.

### Tech Stack

Based on Google guidelines for Android development, I used MVVM with some of common Android Jetpack components:

* <b>ViewModel</b>
* <b>Livedata</b>
* <b>Room</b>

For dependency injection:

* <b>Koin</b>

For Api requests:

* <b>Retrofit</b>
* <b>OkHttp</b>
* <b>GSon</b>

Also this project contains some use of <b>Coroutines</b>, to give an example about how we can use it in production code and tests.

For Unit Tests:

* <b>JUnit4</b>
* <b>MockK</b>

### Continuous Integration

This project comes with Travis CI integration, that nowadays is running three steps in a pipeline:

* <b>Android Lint</b>
* <b>Kotlin Lint</b>
* <b>Unit Tests</b>

Also, note that I created an environment variable there with the **Google Books API Key**. This is required for the correct execution of the pipeline.

#### Dealing with secret API Key

Because I decided to Use **Google Books API** here, is necessary to create a project and enable this API in your Google developer console. By doing this, you'll receive a API Key, that is required for the network requests works.

After having this key, you can put it in a new file in the project, called **apikey.properties**, calling it **GOOGLE_BOOKS_API_KEY**. Doing this the gradle file can read it without problem.

### Blog posts with detailed development process

**(Blog post will be available soon!)**

### Feedbacks and suggestions

For any suggestions, **please create an issue and a pull request** and lets talk about it!

Contact me if you have any feedback, just send a message in my social networks:
* [Twitter](https://twitter.com/DrCabrales)
* [LinkedIn](https://www.linkedin.com/in/drcabral/?locale=en_US)
