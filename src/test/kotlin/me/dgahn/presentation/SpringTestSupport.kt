package me.dgahn.presentation

import me.dgahn.App
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [App::class])
@ActiveProfiles("test")
abstract class SpringTestSupport
