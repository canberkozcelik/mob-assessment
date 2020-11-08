# mob-assessment

Assesment for Mobiquity, coded by Kotlin for Android platform.

## Install  

- Clone repository
- Run the app (minimum SDK version 21)

## Rendered data  

Application consist of two pages:  
Main page - showing list of:  

- Categorized
- Product Image (thumbnail)  
- Product Name

Product Detail page (BottomSheet):  

- Product Image  
- Product Name  
- Product Price  

## Architecture  

MVVM with Repository pattern  
(View - DataBinding - ViewModel - Model)  
  
## Libraries  

- [Architecture Components](https://developer.android.com/arch)
- [Material](https://material.io/develop/android/docs/getting-started)  
- [Android Databinding](https://developer.android.com/topic/libraries/data-binding/index.html)  
- [Dagger Hilt](https://dagger.dev/hilt/) for dependency injection  
- [Kotlin Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) + Flow   
- [Glide](https://github.com/bumptech/glide) for image loading  
- [OkHttp & Retrofit](https://square.github.io/retrofit/) for network calls  
- [SuperBottomSheet](https://github.com/andrefrsousa/SuperBottomSheet) for bottomsheet recipe detail page  
- [Mockito](https://github.com/nhaarman/mockito-kotlin) for mocking with Kotlin  
- [Truth](https://github.com/google/truth) for AndroidTests
