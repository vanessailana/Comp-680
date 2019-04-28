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



def jobRec(userid):

    mydb = mysql.connector.connect(
        host="35.238.104.188",
        user="root",
        passwd="root",
        port=3306, db='680'
        )

    mycursor = mydb.cursor()

    # get jobs 

    mycursor.execute("SELECT * FROM jobs")


    #jobs users can get recommendatiosn
    jobs = pd.DataFrame(mycursor.fetchall());


    mycursor.execute("SELECT *from  users ");

    user_based_approach = pd.DataFrame(mycursor.fetchall());

    mycursor.execute("SELECT * from  applicants");
    apps = pd.DataFrame(mycursor.fetchall());

    user_based_approach[12] = user_based_approach[12].fillna('') 
    user_based_approach[13] = user_based_approach[13].fillna('')
    user_based_approach[16] = str(user_based_approach[16].fillna(''))
    user_based_approach[7] = user_based_approach[12] + user_based_approach[13] + user_based_approach[16]

    tf = TfidfVectorizer(analyzer='word',ngram_range=(1, 2),min_df=0, stop_words='english')
    tfidf_matrix = tf.fit_transform(user_based_approach[7])

    cosine_sim = linear_kernel(tfidf_matrix, tfidf_matrix)


    userid = user_based_approach[0]

    user_based_approach = user_based_approach.reset_index()
    userid = user_based_approach[0]

    indices = pd.Series(user_based_approach.index, index=user_based_approach[0])

    idx = indices[2]

    sim_scores = list(enumerate(cosine_sim[idx]))
        #print (sim_scores)
    sim_scores = sorted(sim_scores, key=lambda x: x[1], reverse=True)
    user_indices = [i[0] for i in sim_scores]

    jobs_userwise = apps[4].isin(user_indices) #
    df1 = pd.DataFrame(data = apps[jobs_userwise])
    joblist = df1[3].tolist()
    Job_list = jobs[0].isin(joblist)


    df_temp = pd.DataFrame(data = jobs[Job_list])
    print (df_temp.to_json(orient='records'))




print(jobRec(2));

