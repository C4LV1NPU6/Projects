# -*- coding: utf-8 -*-
"""Least_squares_II.ipynb

Automatically generated by Colaboratory.

Original file is located at
    https://colab.research.google.com/drive/1xqqBfDFhrNC6p_WTPCpE37e3FXyLWp6B

#**Lab 7 - Least squares II**

Enter your code in the spaces provided. Do not change any of the variable names or function names that are already provided for you. In places where we specify the name of the return value, make sure that your code produces the a value with the correct name.
"""

# Do not edit this cell.

LabID="Lab7"

try:
  from graderHelp import ISGRADEPLOT
except ImportError:
  ISGRADEPLOT = True

"""**Enter your name, section number, and BYU NetID**"""

# Enter your first and last names in between the quotation marks.

first_name="Calvin"

last_name="Pugmire"

# Enter your Math 215 section number in between the quotation marks. 

section_number="001"  

# Enter your BYU NetID in between the quotation marks.  NOT YOUR BYU ID NUMBER! 

BYUNetID="calvin74"

"""**Import the data set**

The simplest way to load the data into Colab is to first download it as a .csv file to your local computer by clicking the link

https://drive.google.com/uc?export=download&id=1iFTaBmnv1X66BO9cV_RP7OxvUr9dNJ-l

This will allow you to download the data as a .csv file.  In the top left corner of this screen you should see a little file folder icon.  Selecting it opens a new window to the left of the notebook with three tabs: "Upload", "Refresh", and "Mount Drive".  Select "Upload". This should bring up a window that allows you to select the file "Lab7data" from your local machine, which will upload the file to your notebook. You will need to do this again if you decide to close your notebook and reopen it at a later time.

Once you've uploaded your file, convert it to a NumPy array called "signal_data" by executing the following cell.
"""

import numpy as np
import pandas as pd

df = pd.read_csv('Lab7data.csv')
signal_data=df.values
signal_data

"""**Problem 1**"""

T=signal_data[:,0]           # Replace the value of 0 with the NumPy vector that contains all of the time values in the array signal_data (the first column).

Y=signal_data[:,1]           # Replace the value of 0 with the NumPy vector that contains all of the signal amplitude values in the array signal_data (the second column).

import matplotlib.pyplot as plt

def create_plots1():
  # Put all of your code to create the plots here.
  plt.plot(T,Y)
  plt.show()
  return None

create_plots1()

# Create the plot here of the data in the vectors T and Y.

"""**Problem 2**"""

# This function returns the row [1,cos(t),sin(t), cos(2*t), sin(2*t) , ... , cos(n*t), sin(n*t)] of our matrix X.

def row_func(t,n):
  r = [f(i*t) for i in range(1,n+1) for f in [np.cos,np.sin]]
  r.insert(0,1)
  return r

"""**Problem 3**"""

# This function returns the matrix X, which we call the design matrix.

def design_matrix(n):
  m = []
  for i in range(T.size):
    m.insert(i,row_func(T[i],n))
  m = np.array(m)
  return m

"""**Problem 4**"""

X2=design_matrix(2)

"""**Problem 5**"""

# Replace all of the 0 values with the NumPy matrices and vectors requested in Problem 5.

normal_coef2=np.matmul(np.transpose(X2),X2)

normal_vect2=np.matmul(np.transpose(X2),Y)

beta2=np.linalg.solve(normal_coef2,normal_vect2)

"""**Problem 6**"""

# This is our function which approximates the signal strength when n=2.

def f2(t):
  f2t = np.array(row_func(t,2))
  return np.dot(f2t,beta2)

"""**Problem 7**"""

vf2=np.vectorize(f2)    # This creates a new function, vf2, which is
                        # the same as f2 except for the fact that is
                        # can take a NumPy array as input, instead of
                        # individual values.

plt.plot(T,Y,'r.')      # Creating the plot of the data points in Y
                        # using small red dots.

plt.plot(T,vf2(T),'b-') # Creating the plot of the function f2 with a
                        # blue line. Here we use the function vf2
                        # instead of f2, since we are plugging in a
                        # NumPy array T.

plt.show()              # Display the plots.

"""**Problem 8**"""

MSE2=(1/Y.size)*np.linalg.norm(np.matmul(X2,beta2)-Y)**2

"""**Problem 9**"""

# Replace the 0 values with the values requested in Problem 9.  Remember to copy the decimal values from your practice notebook, not the formulas you used to compute them.

MSE10=0.009348751458203415

pred10=0.5087757565416515

"""**Problem 10**"""

# Replace the 0 values with the values requested in Problem 10.  Remember to copy the decimal values from your practice notebook, not the formulas you used to compute them.

MSE100=0.0014547562364331283

pred100=0.5089902472113171

"""**Problem 11**"""

# Replace the 0 values with the values requested in Problem 11.  Remember to copy the decimal values from your practice notebook, not the formulas you used to compute them.

MSE1000=2.2450937770053657e-28

pred1000=-7.088223356300462

"""**STOP!  BEFORE YOU SUBMIT THIS LAB:**  Go to the "Runtime" menu at the top of this page, and select "Restart and run all".  If any of the cells produce error messages, you will either need to fix the error(s) or delete the code that is causing the error(s).  Then use "Restart and run all" again to see if there are any new errors.  Repeat this until no new error messages show up.

**You are not ready to submit until you are able to select "Restart and run all" without any new error messages showing up.  Your code will not be able to be graded if there are any error messages.**

To submit your lab for grading you must first download it to your compute as .py file. In the "File" menu select "Download .py". The resulting file can then be uploaded to http://www.math.byu.edu:30000 for grading.
"""