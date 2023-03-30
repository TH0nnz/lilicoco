package com.tomjheng.lilicoco.SUN_NEW.cache2;


import org.apache.commons.collections.MapIterator;
import org.apache.commons.collections.map.LRUMap;

import java.util.ArrayList;

/**
 * @author Crunchify.com
 * How to Create a Simple In Memory Cache in Java (Lightweight Cache)
 */
public class CrunchifyInMemoryCache<K, T> {
    private final long timeToLive;

    // LRUMap: A Map implementation with a fixed maximum size which removes the least recently used entry if an entry is added when full.
    // The least recently used algorithm works on the get and put operations only.
    // Iteration of any kind, including setting the value by iteration, does not change the order.
    // Queries such as containsKey and containsValue or access via views also do not change the order.
    private final LRUMap crunchifyCacheMap;

    protected class CrunchifyCacheObject {

        // currentTimeMillis(): Returns the current time in milliseconds.
        // Note that while the unit of time of the return value is a millisecond,
        // the granularity of the value depends on the underlying operating system and may be larger.
        // For example, many operating systems measure time in units of tens of milliseconds.
        public long lastAccessed = System.currentTimeMillis();
        public T value;

        protected CrunchifyCacheObject(T value) {
            this.value = value;
        }
    }

    public CrunchifyInMemoryCache(long crunchifyTimeToLive, final long crunchifyTimerInterval, int maxItems) {
        this.timeToLive = crunchifyTimeToLive * 1000;
        crunchifyCacheMap = new LRUMap(maxItems);
        if (timeToLive > 0 && crunchifyTimerInterval > 0) {
            Thread t = new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        try {

                            // Thread: A thread is a thread of execution in a program.
                            // The Java Virtual Machine allows an application to have multiple threads of execution running concurrently.
                            Thread.sleep(crunchifyTimerInterval * 1000);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        crunchifyCleanup();
                    }
                }
            });
            // setDaemon(): Marks this thread as either a daemon thread or a user thread.
            // The Java Virtual Machine exits when the only threads running are all daemon threads.
            // This method must be invoked before the thread is started.
            t.setDaemon(true);

            // start(): Causes this thread to begin execution; the Java Virtual Machine calls the run method of this thread.
            // The result is that two threads are running concurrently:
            // the current thread (which returns from the call to the start method) and the other thread (which executes its run method).
            t.start();
        }
    }

    public void put(K key, T value) {
        synchronized (crunchifyCacheMap) {

            // put(): Puts a key-value mapping into this map.
            crunchifyCacheMap.put(key, new CrunchifyCacheObject(value));
        }
    }

    public T get(K key) {
        synchronized (crunchifyCacheMap) {
            CrunchifyCacheObject c;
            c = (CrunchifyCacheObject) crunchifyCacheMap.get(key);
            if (c == null) return null;
            else {
                c.lastAccessed = System.currentTimeMillis();
                return c.value;
            }
        }
    }

    public void remove(K key) {
        synchronized (crunchifyCacheMap) {
            crunchifyCacheMap.remove(key);
        }
    }

    public int size() {
        synchronized (crunchifyCacheMap) {
            return crunchifyCacheMap.size();
        }
    }

    public void crunchifyCleanup() {
        // System: The System class contains several useful class fields and methods.
        // It cannot be instantiated. Among the facilities provided by the System class are standard input, standard output,
        // and error output streams; access to externally defined properties and environment variables;
        // a means of loading files and libraries; and a utility method for quickly copying a portion of an array.
        long now = System.currentTimeMillis();
        ArrayList<K> deleteKey = null;
        synchronized (crunchifyCacheMap) {
            MapIterator itr = crunchifyCacheMap.mapIterator();
            // ArrayList: Constructs an empty list with the specified initial capacity.
            // size(): Gets the size of the map.
            deleteKey = new ArrayList<K>((crunchifyCacheMap.size() / 2) + 1);
            K key = null;
            CrunchifyCacheObject c = null;
            while (itr.hasNext()) {
                key = (K) itr.next();
                c = (CrunchifyCacheObject) itr.getValue();
                if (c != null && (now > (timeToLive + c.lastAccessed))) {

                    // add(): Appends the specified element to the end of this list.
                    deleteKey.add(key);
                }
            }
        }
        for (K key : deleteKey) {
            synchronized (crunchifyCacheMap) {

                // remove(): Removes the specified mapping from this map.
                crunchifyCacheMap.remove(key);
            }
            // yield(): A hint to the scheduler that the current thread is willing to
            // yield its current use of a processor.
            // The scheduler is free to ignore this hint.
            Thread.yield();
        }
    }
}
