# coroutines
Practice project of Advance coroutines 

Program : AsyncTaskMain

Desription: scanario to implement concurrent tasks and how effiently excutes the flow , time comparsion between async and normal execution flow.
Reference -> https://developer.android.com/kotlin/coroutines/coroutines-adv#parallel

Program: SwitchContextMain

Description: withContext does not create a new coroutine, it only shifts the context of the existing coroutine, which is why it's a suspend function (unlike launch and async).
it runs on main thread and switch context of IO while getting remote data.
Reference -> https://developer.android.com/kotlin/coroutines#use-coroutines-for-main-safety
