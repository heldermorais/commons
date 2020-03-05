package common.aop.interceptors

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@Retention(RetentionPolicy.RUNTIME)
@interface CheckBeforeExecution {
  String checker()
}