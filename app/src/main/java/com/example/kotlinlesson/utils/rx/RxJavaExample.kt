package com.example.kotlinlesson.utils.rx

class RxJavaExample {

//    fun observableJust1(){
//        val developers: Observable<String> = Observable.just(
//            "IOS",
//            "Android",
//            "Flutter"
//        )
//
//        developers.doAfterNext {
//            Log.w("next", it)
//        }.doOnError {
//
//        }.doOnComplete {
//            Log.w("completed", "finish")
//        }.subscribe()
//
//
//        val developersAnotherWay: Observable<String> = Observable.just(
//            "IOS",
//            "Android",
//            "Flutter"
//        )
//        developersAnotherWay.subscribe({
//            Log.w("next", it)
//        }, {
//
//        }, {
//            Log.w("completed", "finish")
//        }
//        )
//
//
//        val devList = listOf<String>("IOS", "Android", "Flutter")
//
//        Observable.create<String> { emitted ->
//            devList.forEach { developer ->
//                if (developer.isEmpty()) {
//                    emitted.onError(Exception("value is empty"))
//                }
//                emitted.onNext(developer)
//            }
//        }.doAfterNext {
//            Log.w("next", it)
//        }.doOnError {
//        }.doOnComplete {
//            Log.w("completed", "finish")
//        }.subscribe({}, { Log.w("error", it.message.toString()) })
//    }
//
//    fun flatMapOdservable(){
//        Observable.just("IOS", "Android", "Flutter")
//            .subscribeOn(Schedulers.io())
//            .flatMap {
//                Observable.just("$it 2")
//                    .subscribeOn(Schedulers.io())
//            }
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe {
//                Log.w("result", it.toString())
//            }
//    }
//
//    fun observableZip(){
////        Observable.zip(
////            Observable.just("IOS", "Android", "Flutter"),
////            Observable.just("Swift", "Kotlin", "Dart")
////        ){dev, lang ->
////            Log.w("result zip", "$dev writes in $lang")
////        }.subscribe({Log.w("result zip", it)})
//    }
//
//    fun concat(){
//        val devs = Observable.just("IOS", "Android", "Flutter")
//        val langs = Observable.just("Swift", "Kotlin", "Dart")
//        val comps = Observable.just("Apple", "Google")
//
//        Observable.concat(devs,langs,comps)
//            .subscribe{Log.w("result", it.toString())}
//    }
//
//    fun createObs(){
//
//    }
}