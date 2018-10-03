# Overview

This project provides REST API to interact with the articles database.

# Project Setup / Dependencies

Check application.properties (database credentials)

# HTTP Headers required to access the API
Username: "xxxx"
Password: "xxxx"

# Methods
- register(name, password, email)
- login(name, password): boolean
    *currently this only checks if the credentials is correct.
- labels(): QuestionDTO[]
