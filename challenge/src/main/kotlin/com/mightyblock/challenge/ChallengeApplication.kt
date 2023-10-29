package com.mightyblock.challenge

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

@SpringBootApplication
class ChallengeApplication

fun main(args: Array<String>) {
	runApplication<ChallengeApplication>(*args)
}

