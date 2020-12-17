package com.justclean.zuhaib.laundry

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.justclean.zuhaib.laundry.dagger.modules.commentListService
import com.justclean.zuhaib.laundry.model.CommentModel
import com.justclean.zuhaib.laundry.viewmodel.ListViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.internal.schedulers.ExecutorScheduler.ExecutorWorker
import io.reactivex.plugins.RxJavaPlugins
import org.junit.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.util.concurrent.Callable
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit


class ListViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()
    @Mock
    lateinit var mostViewedService: commentListService

    @InjectMocks
    var listViewModel = ListViewModel()
    lateinit var testSingle: Single<List<CommentModel>>
    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getCountriesSuccess() {



        val result = CommentModel(1, "sunt aut facere repellat provident occaecati excepturi optio reprehenderit", "quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto")
        val resultList = arrayListOf(result)


       testSingle = Single.just(resultList)


        `when`(mostViewedService.getPostComment()).thenReturn(testSingle)

      listViewModel.fetchCommentData()


      Assert.assertEquals(false, listViewModel.postCommentList)

      Assert.assertEquals(false, listViewModel.loading.value)

    }

    @Test
    fun getCountriesFail(){

        testSingle = Single.error(Throwable())

        `when`(mostViewedService.getPostComment()).thenReturn(testSingle)

        listViewModel.fetchCommentData()

        Assert.assertEquals(true,listViewModel.postCommentList)

        Assert.assertEquals(false,listViewModel.loading.value)

    }

    @Before
    fun setUpRxSchedulers(){
        val immidiate = object: Scheduler() {
            override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit): Disposable {
                return super.scheduleDirect(run, delay, unit)
            }


            override fun createWorker(): Worker {
                return ExecutorWorker { obj: Runnable -> obj.run() }
            }
        }

        RxJavaPlugins.setInitIoSchedulerHandler{ scheduler: Callable<Scheduler>? -> immidiate }
        RxJavaPlugins.setInitComputationSchedulerHandler { scheduler: Callable<Scheduler>? -> immidiate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler: Callable<Scheduler>? ->  immidiate}
        RxJavaPlugins.setInitSingleSchedulerHandler { scheduler: Callable<Scheduler>? -> immidiate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler: Callable<Scheduler>? -> immidiate }

    }


//    @BeforeClass
//    fun setUpRxSchedulers() {
//        val immediate: Scheduler = object : Scheduler() {
//            override fun scheduleDirect(@NonNull run: Runnable, delay: Long, @NonNull unit: TimeUnit): Disposable {
//                // this prevents StackOverflowErrors when scheduling with a delay
//                return super.scheduleDirect(run, 0, unit)
//            }
//
//            override fun createWorker(): Worker {
//                return ExecutorWorker { obj: Runnable -> obj.run() }
//            }
//        }
//        RxJavaPlugins.setInitIoSchedulerHandler { scheduler: Callable<Scheduler?>? -> immediate }
//        RxJavaPlugins.setInitComputationSchedulerHandler { scheduler: Callable<Scheduler?>? -> immediate }
//        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler: Callable<Scheduler?>? -> immediate }
//        RxJavaPlugins.setInitSingleSchedulerHandler { scheduler: Callable<Scheduler?>? -> immediate }
//        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler: Callable<Scheduler?>? -> immediate }
//    }

}