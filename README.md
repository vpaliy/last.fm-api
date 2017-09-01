# Last.fm-API
[![](https://jitpack.io/v/vpaliyX/Last.fm-API.svg)](https://jitpack.io/#vpaliyX/Last.fm-API)


This project is a wrapper for the [Last.fm API](https://www.last.fm/api/). 
The Last.fm API allows anyone to build their own programs using Last.fm data. What can I do with this API? 

Here's the list of the most useful things you can do with this API:

- **get** artists, tags, albums, users, tracks, charts
- **get** the most popular items (artists,tracks,albums,tags)
- **get** info of an item.
- **get** a list of the most popular artists or tracks by a country name.
- **add/remove** a tag to/from an artist, track, album
- **like/unlike** a track 
- **retrieve** data from a user (favorite items,tracks, albums, info, recent tracks, charts)
- **search** for an item.
- **log in/log out**

## How do I use this wrapper? ##

### Step 1 ###  

Add this to your root `build.gradle` file:

``` gradle
allprojects {
  repositories {
     maven { url 'https://jitpack.io' }
  }
}
```
### Step 2 ###

Add the dependency

``` gradle
dependencies {
     compile 'com.github.vpaliyX:Last.fm-API:v1.0'
}

```

## Nuts and Bolts ##

This wrapper is built with Retrofit2 and RxJava2. Basically, the wrapper is divided into 3 main abstractions:
- **Authentication** - authentication of a user, provides a session key.
- **Update Service** - all write requests (POST).
- **Last.fm Service** - all read requests (GET).

Regardless which request you want to make, you need to have an API_KEY and a SECRET_KEY. You can obtain this [here](https://www.last.fm/api/account/create), it takes only 2 min to get it.

**Note** 
Due to the complex structure of JSON data returned by the web service, all requests are using a wrapper called [Response](https://github.com/vpaliyX/Last.fm-API/blob/master/last-fm-api/src/main/java/com/vpaliy/last_fm_api/model/Response.java) which is the response of a request. Just access the [Response.result](https://github.com/vpaliyX/Last.fm-API/blob/master/last-fm-api/src/main/java/com/vpaliy/last_fm_api/model/Response.java#L13) field of that object, and you will get your desired data. 

## Authentication ## 

It's super simple. 

First of all, **Last.fm** provides you with a **session key** which *never expires*.
So you don't have to refresh this at all. Once you have obtained you session key, you can do any of the POST requests.

Secondly, if you don't want to do POST requests then you don't need a session key. You can skip this step because you don't need to have a session key for GET requests.

The [LastFmAuth](https://github.com/vpaliyX/Last.fm-API/blob/master/last-fm-api/src/main/java/com/vpaliy/last_fm_api/auth/LastFmAuth.java) class is responsible for the authorization, it encapsulates some additional steps, you just need to provide a **username** and **password**. Once you've done that, 
you will receive a session key.

Here's how it should be done:

```java
LastFmAuth.create(Config.API_KEY,Config.API_SECRET)
      .auth(context,"username","password")
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(response -> {
          Session session=response.result;
       });

```

**Note**
The [Session](https://github.com/vpaliyX/Last.fm-API/blob/master/last-fm-api/src/main/java/com/vpaliy/last_fm_api/model/Session.java) object, which has been provided after the call, will be needed to perform POST requests, 
so you can just save it in the shared preferences with one helper method:

```java
   SharedPreferences.Editor editor=pref.edit();
   editor.putString("key",Session.convertToString(session)).apply();
```
And if you need this again, you can just retrieve from your shared preferences:

```java

Session session=Session.convertFromString(pref.getString("key",null));

```

## Update Service ## 

Here you need to provide only a `Context` and a `Session` object. You can access the write calls with the [LastFmUpdate](https://github.com/vpaliyX/Last.fm-API/blob/master/last-fm-api/src/main/java/com/vpaliy/last_fm_api/auth/LastFmUpdate.java) class, here is an example:

```java
  LastFmUpdate.create(context,session)
      .addTagsToAlbum("artist","album","tag1","tag2","tag3")
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(()->{},Throwable::printStackTrace);
```

Also, you can use chains, so you can make a few requests at the same time:

```java
LastFmUpdate.create(this,session)
       .startChain()
       .addTagsToAlbum("Imagine Dragons","Evolve","#favorite","#album")
       .addTagsToArtist("Imagine Dragons","#favorite","#lovely")
       .unloveTrack("Imagine Dragons","Demons")
       .loveTrack("Imagine Dragons","Whatever It Takes")
       .addTagsToTrack("Imagine Dragons","Rise Up","#summer2017")
       .addTagsToTrack("Imagine Dragons","Walking the Wire","#summer2017")
       .stop()
       .subscribeOn(Schedulers.io())
       .observeOn(AndroidSchedulers.mainThread())
       .subscribe(()->{},Throwable::printStackTrace);
```

Use the `startChain()` method to create a chain, and the `stop()` method to stop it.

All POST requests return a [Completable](http://reactivex.io/RxJava/2.x/javadoc/io/reactivex/Completable.html) object, which represents a deferred computation without any value but only indication for completion or exception. It's either success or failure when you subscribe to it.

## Last.fm Service ##

You can access this service without any Session object. It just works.

Use the [LastFm](https://github.com/vpaliyX/Last.fm-API/blob/master/last-fm-api/src/main/java/com/vpaliy/last_fm_api/LastFm.java) class to create the service:

```java
 //get the service
 LastFmService service=LastFm.create(Config.API_KEY)
            .createService(this);
            
//request an artist      
service.fetchArtist("name of an artist")
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(response -> {
            Artist artist=response.result;
       });
```

## Additional Documentation And Support ##
- The [Last.fm API Documentation](https://www.last.fm/api/intro).
- If you have any questions or you have found some issues, feel free to write in the [Issue Section](https://github.com/vpaliyX/Last-fm-API/issues).



## The End. ##

``````
MIT License

Copyright (c) 2017 Vasyl Paliy

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
``````

