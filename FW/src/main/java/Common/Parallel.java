package Common;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * Created by Administrator on 2017-09-22.
 */
public class Parallel {
//    private Thread[] Wind_Thread;//线程数组对象
//    private Thread _DoThread;//线程数组对象
//    public int _ThreadNum = 5;//线程数
//    private  readonly object _SyncLock = new object();
//    public  bool _IsExit = false;
//    public  Queue _QueueData = new Queue();//存放要处理数据的对列
//    public  ReaderWriterLock oLogLock = new ReaderWriterLock();//锁对象
    private static Parallel item;
    public static Parallel Instance() {
        if (item == null) {
            item = new Parallel();
        }
        return item;
    }
    public class ThreadData {
        public int Index;
        public Object Data;
        public CountDownEvent WaitHandle;
    }
    public  void For(int len, Consumer<Integer> action, Consumer<ParallelException> handleError, long timeout) {
        CountDownEvent evt = new CountDownEvent(len);
        AtomicInteger number = new AtomicInteger();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        try {
            for (int i = 0; i < len; i++) {
                i = number.getAndIncrement();
                int finalI = i;
                ThreadData mThreadData = new ThreadData() {{
                    Index = finalI;
                    Data = item;
                    WaitHandle = evt;
                }};
                fixedThreadPool.execute(new Handler<ThreadData>(mThreadData) {
                    @Override
                    public void run() {
                        ThreadData shared = (ThreadData) this.mObject;
                        try {
                            action.accept(shared.Index);
                        } catch (Exception e) {
                            ParallelException mParallelException = new ParallelException(shared.Index);
                            handleError.accept(mParallelException);
                        } finally {
                            shared.WaitHandle.Signal();
                        }
                    }
                });
            }
            evt.Wait(timeout);
        } catch (Exception ex) {
            ParallelException mParallelException = new ParallelException(0);
            handleError.accept(mParallelException);
        }
        fixedThreadPool.shutdown();
    }

//    public void For<T>(IEnumerable<T> source, Action<T> action, Action<T, Exception> handleError, TimeSpan timeout)
//    {
//        AggregateException errors = new AggregateException();
//        source.ForEach(p =>
//                {
//                        SetObject(p);
//            });
//        using (CountdownEvent evt = new CountdownEvent(_ThreadNum))
//        {
//            int i = 0;
//            Wind_Thread = new Thread[_ThreadNum];//设置分线程数
//            for (int Wind_I = 0; Wind_I < _ThreadNum; Wind_I++)
//            {
//                Interlocked.Increment(ref i);
//                Wind_Thread[Wind_I] = new Thread(new ParameterizedThreadStart(obj =>
//                        {
//                                var shared = obj as Parallel.ThreadData;
//                T Wind_Obj = GetObject<T>();//获取操作对象
//                while (!Wind_Obj.IsNullOrEmpty() || !_IsExit)
//                {
//                    try
//                    {
//                        action(Wind_Obj);
//                    }
//                    catch (Exception ex)
//                    {
//                        handleError(Wind_Obj, ex);
//                        errors.AddException(shared.Index, ex);
//                    }
//                    Wind_Obj = GetObject<T>();//获取操作对象
//                }
//                shared.WaitHandle.Signal();
//                    }));//多线程同步开始
//                Wind_Thread[Wind_I].IsBackground = true;
//                Wind_Thread[Wind_I].Name = Convert.ToString(i);
//                Wind_Thread[Wind_I].Start(new ThreadData { Index = i, WaitHandle = evt });
//            }
//            evt.Wait(timeout);
//        }
//        if (errors.Count > 0) throw errors;
//    }

    public <T> void  ForEach(List<T> source, Consumer<T> action, Consumer<ParallelException> handleError, Long timeout) {
        CountDownEvent evt = new CountDownEvent(source.size());
        AtomicInteger number = new AtomicInteger();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        int i = 0;
        try {
            for (T item : source) {
                i=number.getAndIncrement();
                int finalI = i;
                ThreadData mThreadData=new ThreadData(){{
                    Index=finalI;
                    Data = item;
                    WaitHandle = evt;
                }};
                fixedThreadPool.execute(new Handler<ThreadData>(mThreadData){
                    @Override
                    public void run() {
                        ThreadData shared = (ThreadData)this.mObject;
                        try {
                            action.accept((T)shared.Data);
                        } catch (Exception e) {
                            ParallelException mParallelException=new ParallelException(shared.Index);
                            handleError.accept(mParallelException);
                        }
                        finally {
                            shared.WaitHandle.Signal();
                        }
                    }
                });
            }
            evt.Wait(timeout);
        } catch (Exception ex) {
            ParallelException mParallelException=new ParallelException(0);
            handleError.accept(mParallelException);
        }
        fixedThreadPool.shutdown();
    }

//    public  ConcurrentDictionary<int, TResult> ForEach<T, TResult>(IEnumerable<T> source, Func<T, TResult> func, Action<T, Exception> handleError, TimeSpan timeout)
//    {
//        ConcurrentDictionary<int, TResult> dict = new ConcurrentDictionary<int, TResult>();
//        AggregateException errors = new AggregateException();
//        using (CountdownEvent evt = new CountdownEvent(source.Count()))
//        {
//            int i = 0;
//            foreach (var item in source)
//            {
//                Interlocked.Increment(ref i);
//                ThreadPool.QueueUserWorkItem(obj =>
//                        {
//                                var shared = obj as Parallel.ThreadData;
//                try
//                {
//                    dict[shared.Index] = func(shared.Data.ConvertType<T>());
//                }
//                catch (Exception ex)
//                {
//                    handleError(shared.Data.ConvertType<T>(), ex);
//                    errors.AddException(shared.Index, ex);
//                }
//                finally
//                {
//                    shared.WaitHandle.Signal();
//                }
//
//                    }, new ThreadData { Index = i, Data = item, WaitHandle = evt });
//            }
//            evt.Wait(timeout);
//        }
//        if (errors.Count > 0) throw errors;
//        return dict;
//    }

//    public  int DoEach<T>(Action<T> action, Action<T, Exception> handleError, TimeSpan timeout)
//    {
//        AggregateException errors = new AggregateException();
//        using (CountdownEvent evt = new CountdownEvent(_ThreadNum))
//        {
//            int i = 0;
//            Wind_Thread = new Thread[_ThreadNum];//设置分线程数
//            for (int Wind_I = 0; Wind_I < _ThreadNum; Wind_I++)
//            {
//                Interlocked.Increment(ref i);
//                Wind_Thread[Wind_I] = new Thread(new ParameterizedThreadStart(obj =>
//                        {
//                                var shared = obj as Parallel.ThreadData;
//                T Wind_Obj = GetObject<T>();//获取操作对象
//                while (!Wind_Obj.IsNullOrEmpty())
//                {
//                    try
//                    {
//                        action(Wind_Obj);
//                    }
//                    catch (Exception ex)
//                    {
//                        handleError(Wind_Obj, ex);
//                        errors.AddException(shared.Index, ex);
//                    }
//                    Wind_Obj = GetObject<T>();//获取操作对象
//                }
//                shared.WaitHandle.Signal();
//                    }));//多线程同步开始
//                Wind_Thread[Wind_I].IsBackground = true;
//                Wind_Thread[Wind_I].Name = Convert.ToString(Wind_I);
//                Wind_Thread[Wind_I].Start(new ThreadData { Index = i, WaitHandle = evt });
//            }
//            evt.Wait(timeout);
//        }
//        StopRun();
//        Wind_Thread = null;
//        return errors.Count;
//    }

//    public  int DoEach<T>(Action<int> GetParaAction, Action<T> DoAction, Action<T, Exception> handleError, TimeSpan timeout)
//    {
//        AggregateException errors = new AggregateException();
//        using (CountdownEvent evt = new CountdownEvent(_ThreadNum))
//        {
//            int i = 0;
//            Interlocked.Increment(ref i);
//            _DoThread = new Thread(new ParameterizedThreadStart(obj =>
//                    {
//                            var shared = obj as Parallel.ThreadData;
//            try
//            {
//                GetParaAction(shared.Index);
//            }
//            catch (Exception ex)
//            {
//                //handleError(shared.Data, ex);
//                errors.AddException(shared.Index, ex);
//            }
//            shared.WaitHandle.Signal();
//                }));//线程同步开始
//            _DoThread.IsBackground = true;
//            _DoThread.Name = Convert.ToString(i);
//            _DoThread.Start(new ThreadData { Index = i, WaitHandle = evt });
//
//            Wind_Thread = new Thread[_ThreadNum];//设置分线程数
//            for (int Wind_I = 0; Wind_I < _ThreadNum; Wind_I++)
//            {
//                Interlocked.Increment(ref i);
//                Wind_Thread[Wind_I] = new Thread(new ParameterizedThreadStart(obj =>
//                        {
//                                var shared = obj as Parallel.ThreadData;
//                T Wind_Obj = GetObject<T>();//获取操作对象
//                while (!Wind_Obj.IsNullOrEmpty() || !_IsExit)
//                {
//                    try
//                    {
//                        DoAction(Wind_Obj);
//                    }
//                    catch (Exception ex)
//                    {
//                        handleError(Wind_Obj, ex);
//                        errors.AddException(shared.Index, ex);
//                    }
//                    Wind_Obj = GetObject<T>();//获取操作对象
//                }
//                shared.WaitHandle.Signal();
//                    }));//多线程同步开始
//                Wind_Thread[Wind_I].IsBackground = true;
//                Wind_Thread[Wind_I].Name = Convert.ToString(i);
//                Wind_Thread[Wind_I].Start(new ThreadData { Index = i, WaitHandle = evt });
//            }
//            evt.Wait(timeout);
//        }
//        StopRun();
//        Wind_Thread = null;
//        return errors.Count;
//    }
//    public  T GetObject<T>()
//    {
//        oLogLock.AcquireWriterLock(System.Threading.Timeout.Infinite);//多线程锁
//        //取数据
//        if (_QueueData.Count < 1)
//        {
//            oLogLock.ReleaseWriterLock();
//            return default(T);//小于1取null
//        }
//        else
//        {
//            try
//            {
//                T Wind_Obj = _QueueData.Dequeue().ConvertType<T>();//取对列值
//                oLogLock.ReleaseWriterLock();
//                return Wind_Obj;
//            }
//            catch
//            {
//                oLogLock.ReleaseWriterLock();
//                return default(T);//出错返回一个新对象ID为0
//            }
//        }
//    }
    public void SetObject(Object WindObject) {
//        oLogLock.AcquireWriterLock(System.Threading.Timeout.Infinite);//多线程锁
//        _QueueData.Enqueue(WindObject);
//        oLogLock.ReleaseWriterLock();
    }

    public void StopRun() {
//        for (int Wind_I = 0; Wind_I <= _ThreadNum - 1; Wind_I++)
//        {
//            try
//            {
//                if ((Wind_Thread[Wind_I].ThreadState != System.Threading.ThreadState.Stopped && Wind_Thread[Wind_I].ThreadState != System.Threading.ThreadState.Aborted))
//                {
//                    Wind_Thread[Wind_I].Abort();
//                }
//            }
//            catch
//            {
//                //Application.ExitThread();
//            }
//        }
    }
}
class Handler<T> implements Runnable {
    protected T mObject;
    Handler(T para) { this.mObject = para; }
    public void run() {

    }
}
