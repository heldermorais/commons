package common.autorun

import groovy.transform.AnnotationCollector

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Autorun
@AnnotationCollector
@interface OnApplicationReady {

}