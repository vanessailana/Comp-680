import numpy as np
import pandas as pd
import mysql.connector
import matplotlib.pyplot as plt
import seaborn as sns
import ast 
import mysql.connector
from scipy import stats
from ast import literal_eval
from sklearn.feature_extraction.text import TfidfVectorizer, CountVectorizer
from sklearn.metrics.pairwise import linear_kernel, cosine_similarity


mydb = mysql.connector.connect(
    host="35.238.104.188",
    user="root",
    passwd="root",
    port=3306, db='680'
    )

mycursor = mydb.cursor()

mycursor.execute("SELECT * FROM user_history")

myresult = pd.DataFrame(mycursor.fetchall());

myresult.columns=mycursor.column_names;


apps=myresult;

# get jobs 

mycursor.execute("SELECT * FROM jobs")

jobs = pd.DataFrame(mycursor.fetchall());

jobs.columns=mycursor.column_names;

job_listing=jobs;

print(job_listing);


#get users 

mycursor.execute("SELECT * FROM users_ml")

users= pd.DataFrame(mycursor.fetchall());

users.columns=mycursor.column_names;

user_metadata=users;

print(user_metadata);


user_metadata = user_metadata.reset_index()
userid = user_metadata['UserID']
indices = pd.Series(user_metadata.index, index=user_metadata['UserID'])

user_metadata['DegreeType'] = user_metadata['DegreeType'].fillna('')
user_metadata['Major'] = user_metadata['Major'].fillna('')
user_metadata['TotalYearsExperience'] = str(user_metadata['TotalYearsExperience'].fillna(''))
user_metadata['DegreeType'] = user_metadata['DegreeType'] + user_metadata['Major']+  user_metadata['Major'] + user_metadata['TotalYearsExperience'];

tf = TfidfVectorizer(analyzer='word',ngram_range=(1, 2),min_df=0, stop_words='english')
tfidf_matrix = tf.fit_transform(user_metadata['DegreeType'])

cosine_sim = linear_kernel(tfidf_matrix, tfidf_matrix)

user_metadata= user_metadata.reset_index()
userid = user_metadata['UserID']
indices = pd.Series(user_metadata.index, index=user_metadata['UserID'])


#cosine similarity 
def get_recommendations_userwise(userid):
    idx = indices[userid]
    #print (idx)
    sim_scores = list(enumerate(cosine_sim[idx]))
    #print (sim_scores)
    sim_scores = sorted(sim_scores, key=lambda x: x[1], reverse=True)
    user_indices = [i[0] for i in sim_scores]
    #print (user_indices)
    return user_indices;


userSim=get_recommendations_userwise(2);


jobs_userwise = apps['UserID'].isin([0, 2, 5, 6, 4, 7, 1, 3]) #

#association
df1 = pd.DataFrame(data = apps[jobs_userwise], columns=['JobID'])