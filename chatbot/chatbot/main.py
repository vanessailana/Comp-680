from flask import Flask, render_template, request
from chatterbot import ChatBot
from chatterbot.trainers import ListTrainer
import os


#create the bot
bot = ChatBot('Friend') #create the bot
bot.set_trainer(ListTrainer) # Teacher
#bot.train(conv) # teacher train the bot