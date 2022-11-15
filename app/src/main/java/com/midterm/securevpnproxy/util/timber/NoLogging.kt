package com.midterm.securevpnproxy.util.timber

import timber.log.Timber


/**
 * No logging in production mode
 * Override functions without super.
 */
class NoLogging : Timber.DebugTree() {
    override fun d(message: String?, vararg args: Any?) {

    }

    override fun d(t: Throwable?) {
    }

    override fun d(t: Throwable?, message: String?, vararg args: Any?) {
    }

    override fun e(message: String?, vararg args: Any?) {
    }

    override fun e(t: Throwable?) {
    }

    override fun e(t: Throwable?, message: String?, vararg args: Any?) {
    }

    override fun i(message: String?, vararg args: Any?) {
    }

    override fun i(t: Throwable?) {
    }

    override fun i(t: Throwable?, message: String?, vararg args: Any?) {
    }

    override fun log(priority: Int, message: String?, vararg args: Any?) {
    }

    override fun log(priority: Int, t: Throwable?) {
    }

    override fun log(priority: Int, t: Throwable?, message: String?, vararg args: Any?) {
    }

    override fun v(message: String?, vararg args: Any?) {
    }

    override fun v(t: Throwable?) {
    }

    override fun v(t: Throwable?, message: String?, vararg args: Any?) {
    }

    override fun w(message: String?, vararg args: Any?) {
    }

    override fun w(t: Throwable?) {
    }

    override fun w(t: Throwable?, message: String?, vararg args: Any?) {
    }

    override fun wtf(message: String?, vararg args: Any?) {
    }

    override fun wtf(t: Throwable?) {
    }

    override fun wtf(t: Throwable?, message: String?, vararg args: Any?) {
    }
}
