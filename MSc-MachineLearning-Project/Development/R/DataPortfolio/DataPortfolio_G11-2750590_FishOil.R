#------------------------------STUDENT NR------------------------------

# Data portfolio
# Student nr. 2750590
# 27th Sep 2021 - 22nd Oct 2021

#------------------------------LIBRARIES------------------------------
library(psych)
library(ggplot2)
library(ggpubr)
library(lmtest)
library(QuantPsyc)
library(pastecs)
library(pracma)
library(arules)
library(ggplot2)
library(grid)
library(ggm)
library(gridExtra)
library(polycor)
library(Hmisc)
library(checkmate)
library(car)
library(pastecs)
library(DescTools)
library(Hmisc)
library(multcomp)
library(haven)
library(reshape2)
library(ez)
library(nlme)
library(MASS)
library(lme4)
library(emmeans)
library(afex)
library(ggbeeswarm)
library(tidyverse)
library(rstatix)

#------------------------------CUSTOM FUNCTIONS------------------------------

skewKurtSE = function(x) {
  n <- length(na.omit(x))
  skew <- describe(x)$skew
  kurt <- describe(x)$kurtosis
  skew_se <- sqrt( ( 6 * n * (n-1)) / ( (n-2) * (n+1) * (n+3) ) )
  kurt_se <- sqrt( (4 * (n^2 - 1) * skew_se^2) / ( (n-3) * (n+5)) )
  return(data.frame(Skew=skew, Skew_SE=skew_se, Kurtosis=kurt, Kurtosis_SE=kurt_se))
}

#------------------------------DATA INTEGRITY, SUBSETTING------------------------------

# Read the data
dataFO = read.csv("fishoil.csv")

# Check data integrity
head(dataFO)
tail(dataFO)
dim(dataFO)
names(dataFO)
summary(dataFO)
psych::describe(dataFO)

# Subset 0mg dosage group (noFO - no Fish Oil)
noFO = subset(dataFO, dataFO$dose==0)
head(noFO)
psych::describe(noFO)

# Examine IQ and ICV frequencies
table(noFO$IQ)
table(noFO$ICV)

# Range for IQ: 56, range for ICV: 26
# Greatest common denominator/divisor for both ranges
GCD(56, 26) # is 2
# It makes sense to create two subsets/quantiles - lower and upper to subset subjects
# based on their ICV and IQ

# Label data with upper and lower quantiles for IQ and ICV
noFO$QuantileIQ = ifelse(noFO$IQ>78 & noFO$IQ<108, "lower", "upper")
noFO$QuantileICV = ifelse(noFO$ICV>86 & noFO$ICV<101, "lower", "upper")

#------------------------------DESCRIPTIVE STATISTICS------------------------------

# Examine descriptive statistics for time variable grouped by ICV quantiles
describeBy(noFO$time_0, group=noFO$QuantileICV)
describeBy(noFO$time_1, group=noFO$QuantileICV)
describeBy(noFO$time_2, group=noFO$QuantileICV)
describeBy(noFO$time_3, group=noFO$QuantileICV)

# Examine descriptive statistics for time variable grouped by IQ quantiles
describeBy(noFO$time_0, group=noFO$QuantileIQ)
describeBy(noFO$time_1, group=noFO$QuantileIQ)
describeBy(noFO$time_2, group=noFO$QuantileIQ)
describeBy(noFO$time_3, group=noFO$QuantileIQ)

# Examine descriptive statistics for score variable grouped by ICV quantiles
describeBy(noFO$score_0, group=noFO$QuantileICV)
describeBy(noFO$score_1, group=noFO$QuantileICV)
describeBy(noFO$score_2, group=noFO$QuantileICV)
describeBy(noFO$score_3, group=noFO$QuantileICV)

# Examine descriptive statistics for score variable grouped by IQ quantiles
describeBy(noFO$score_0, group=noFO$QuantileIQ)
describeBy(noFO$score_1, group=noFO$QuantileIQ)
describeBy(noFO$score_2, group=noFO$QuantileIQ)
describeBy(noFO$score_3, group=noFO$QuantileIQ)

# Create a subset of lower ICV quantile
lowerICV = subset(noFO, noFO$QuantileICV=="lower")

# Create a subset of upper ICV quantile
upperICV = subset(noFO, noFO$QuantileICV=="upper")

# Create a subset of lower IQ quantile
lowerIQ = subset(noFO, noFO$QuantileIQ=="lower")

# Create a subset of upper IQ quantile
upperIQ = subset(noFO, noFO$QuantileIQ=="upper")

#------------------------------NORMALITY TESTING FOR ICV AND IQ QUANTILES------------------------------

# Check normality of time variable by ICV quantiles

# lowerICV W=0.95 p=0.044
by(noFO$time_0, noFO$QuantileICV, stat.desc, norm=T)

by(noFO$time_1, noFO$QuantileICV, stat.desc, norm=T)
by(noFO$time_2, noFO$QuantileICV, stat.desc, norm=T)
by(noFO$time_3, noFO$QuantileICV, stat.desc, norm=T)

# Check normality of time variable by IQ quantiles
by(noFO$time_0, noFO$QuantileIQ, stat.desc, norm=T)
by(noFO$time_1, noFO$QuantileIQ, stat.desc, norm=T)
by(noFO$time_2, noFO$QuantileIQ, stat.desc, norm=T)
by(noFO$time_3, noFO$QuantileIQ, stat.desc, norm=T)

# Check normality of score variable by ICV quantiles
by(noFO$score_0, noFO$QuantileICV, stat.desc, norm=T)
by(noFO$score_1, noFO$QuantileICV, stat.desc, norm=T)
by(noFO$score_2, noFO$QuantileICV, stat.desc, norm=T)
by(noFO$score_3, noFO$QuantileICV, stat.desc, norm=T)

# Check normality of score variable by IQ quantiles
by(noFO$score_0, noFO$QuantileIQ, stat.desc, norm=T)
by(noFO$score_1, noFO$QuantileIQ, stat.desc, norm=T)
by(noFO$score_2, noFO$QuantileIQ, stat.desc, norm=T)
by(noFO$score_3, noFO$QuantileIQ, stat.desc, norm=T)

# Label data with relative time improvements (RTI) - relative to baseline time (time_0)
noFO$RTI1 = ((noFO$time_1*100/noFO$time_0)-100)
noFO$RTI2 = ((noFO$time_2*100/noFO$time_0)-100)
noFO$RTI3 = ((noFO$time_3*100/noFO$time_0)-100)

# Label data with relative score improvements (RSI) - relative to baseline score (score_0)
noFO$RSI1 = ((noFO$score_1*100/noFO$score_0)-100)
noFO$RSI2 = ((noFO$score_2*100/noFO$score_0)-100)
noFO$RSI3 = ((noFO$score_3*100/noFO$score_0)-100)

# Check normality of RTI variable by ICV quantiles
by(noFO$RTI1, noFO$QuantileICV, stat.desc, norm=T)

# lower ICV W=94, p=0.013
by(noFO$RTI2, noFO$QuantileICV, stat.desc, norm=T)
# lower ICV SW=0.94, p=0.022
by(noFO$RTI3, noFO$QuantileICV, stat.desc, norm=T)

# Check normality of RTI variable by IQ quantiles
by(noFO$RTI1, noFO$QuantileIQ, stat.desc, norm=T)

# lower IQ W=0.93, p=0.0087
by(noFO$RTI2, noFO$QuantileIQ, stat.desc, norm=T)
by(noFO$RTI3, noFO$QuantileIQ, stat.desc, norm=T)

# Check normality of RSI variable by ICV quantiles
by(noFO$RSI1, noFO$QuantileICV, stat.desc, norm=T)
by(noFO$RSI2, noFO$QuantileICV, stat.desc, norm=T)
by(noFO$RSI3, noFO$QuantileICV, stat.desc, norm=T)

# Check normality of RSI variable by IQ quantiles
by(noFO$RSI1, noFO$QuantileIQ, stat.desc, norm=T)

# lower IQ W=0.95, p=0.04
by(noFO$RSI2, noFO$QuantileIQ, stat.desc, norm=T)
by(noFO$RSI3, noFO$QuantileIQ, stat.desc, norm=T)

#------------------------------HISTOGRAMS------------------------------

# Visually check time distribution across weeks for all placebo subjects
hist.time_0 <- ggplot(noFO, aes(time_0)) +
  ggtitle("Time 0")+
  geom_histogram(aes(y=..density..), colour="black", fill="white",bins=7) + 
  labs(x = "Time (ms)", y = "Density")  +         
  stat_function(fun = dnorm,
                args = list(mean = mean(noFO$time_0, na.rm = TRUE),
                            sd = sd(noFO$time_0, na.rm = TRUE)), colour = "red", size = 1)

hist.time_1 <- ggplot(noFO, aes(time_1)) +
  ggtitle("Time 1")+
  geom_histogram(aes(y=..density..), colour="black", fill="white",bins=7) + 
  labs(x = "Time (ms)", y = "Density")  +         
  stat_function(fun = dnorm,
                args = list(mean = mean(noFO$time_1, na.rm = TRUE),
                            sd = sd(noFO$time_1, na.rm = TRUE)), colour = "red", size = 1)

hist.time_2 <- ggplot(noFO, aes(time_2)) +
  ggtitle("Time 2")+
  geom_histogram(aes(y=..density..), colour="black", fill="white",bins=7) + 
  labs(x = "Time (ms)", y = "Density")  +         
  stat_function(fun = dnorm,
                args = list(mean = mean(noFO$time_2, na.rm = TRUE),
                            sd = sd(noFO$time_2, na.rm = TRUE)), colour = "red", size = 1)

hist.time_3 <- ggplot(noFO, aes(time_3)) +
  ggtitle("Time 3")+
  geom_histogram(aes(y=..density..), colour="black", fill="white",bins=7) + 
  labs(x = "Time (ms)", y = "Density")  +         
  stat_function(fun = dnorm,
                args = list(mean = mean(noFO$time_3, na.rm = TRUE),
                            sd = sd(noFO$time_3, na.rm = TRUE)), colour = "red", size = 1)

hist.times = arrangeGrob(hist.time_0, hist.time_1, hist.time_2, hist.time_3 ,ncol=2,nrow=2)
grid.draw(hist.times) # interactive device
ggsave("hist.times.pdf", hist.times) # need to specify what to save explicitly

# Visually check score distribution across weeks for all placebo subjects
hist.score_0 <- ggplot(noFO, aes(score_0)) +
  ggtitle("Score 0")+
  geom_histogram(aes(y=..density..), colour="black", fill="white",bins=7) + 
  labs(x = "Score", y = "Density")  +         
  stat_function(fun = dnorm,
                args = list(mean = mean(noFO$score_0, na.rm = TRUE),
                            sd = sd(noFO$score_0, na.rm = TRUE)), colour = "red", size = 1)

hist.score_1 <- ggplot(noFO, aes(score_1)) +
  ggtitle("Score 1")+
  geom_histogram(aes(y=..density..), colour="black", fill="white",bins=7) + 
  labs(x = "Score", y = "Density")  +         
  stat_function(fun = dnorm,
                args = list(mean = mean(noFO$score_1, na.rm = TRUE),
                            sd = sd(noFO$score_1, na.rm = TRUE)), colour = "red", size = 1)

hist.score_2 <- ggplot(noFO, aes(score_2)) +
  ggtitle("Score 2")+
  geom_histogram(aes(y=..density..), colour="black", fill="white",bins=7) + 
  labs(x = "Score", y = "Density")  +         
  stat_function(fun = dnorm,
                args = list(mean = mean(noFO$score_2, na.rm = TRUE),
                            sd = sd(noFO$score_2, na.rm = TRUE)), colour = "red", size = 1)

hist.score_3 <- ggplot(noFO, aes(score_3)) +
  ggtitle("Score 3")+
  geom_histogram(aes(y=..density..), colour="black", fill="white",bins=7) + 
  labs(x = "Score", y = "Density")  +         
  stat_function(fun = dnorm,
                args = list(mean = mean(noFO$score_3, na.rm = TRUE),
                            sd = sd(noFO$score_3, na.rm = TRUE)), colour = "red", size = 1)

hist.scores = arrangeGrob(hist.score_0, hist.score_1, hist.score_2, hist.score_3, ncol=2, nrow=2)
grid.draw(hist.scores) # interactive device
ggsave("hist.scores.pdf", hist.scores) # need to specify what to save explicitly

# Visually check time distribution in across weeks in lower ICV
hist.time_0 <- ggplot(lowerICV, aes(time_0)) +
  ggtitle("Time 0 - Lower ICV")+
  geom_histogram(aes(y=..density..), colour="black", fill="white",bins=7) + 
  labs(x = "Time (ms)", y = "Density")  +         
  stat_function(fun = dnorm,
                args = list(mean = mean(lowerICV$time_0, na.rm = TRUE),
                            sd = sd(lowerICV$time_0, na.rm = TRUE)), colour = "red", size = 1)

hist.time_1 <- ggplot(lowerICV, aes(time_1)) +
  ggtitle("Time 1 - Lower ICV")+
  geom_histogram(aes(y=..density..), colour="black", fill="white",bins=7) + 
  labs(x = "Time (ms)", y = "Density")  +         
  stat_function(fun = dnorm,
                args = list(mean = mean(lowerICV$time_1, na.rm = TRUE),
                            sd = sd(lowerICV$time_1, na.rm = TRUE)), colour = "red", size = 1)

hist.time_2 <- ggplot(lowerICV, aes(time_2)) +
  ggtitle("Time 2 - Lower ICV")+
  geom_histogram(aes(y=..density..), colour="black", fill="white",bins=7) + 
  labs(x = "Time (ms)", y = "Density")  +         
  stat_function(fun = dnorm,
                args = list(mean = mean(lowerICV$time_2, na.rm = TRUE),
                            sd = sd(lowerICV$time_2, na.rm = TRUE)), colour = "red", size = 1)

hist.time_3 <- ggplot(lowerICV, aes(time_3)) +
  ggtitle("Time 3 - Lower ICV")+
  geom_histogram(aes(y=..density..), colour="black", fill="white",bins=7) + 
  labs(x = "Time (ms)", y = "Density")  +         
  stat_function(fun = dnorm,
                args = list(mean = mean(lowerICV$time_3, na.rm = TRUE),
                            sd = sd(lowerICV$time_3, na.rm = TRUE)), colour = "red", size = 1)

hist.times.lowerICV = arrangeGrob(hist.time_0, hist.time_1, hist.time_2, hist.time_3 ,ncol=2,nrow=2)
grid.draw(hist.times.lowerICV) # interactive device
ggsave("hist.times.lowerICV.pdf", hist.times.lowerICV) # need to specify what to save explicitly

# Visually check time distribution in across weeks in upper ICV
hist.time_0 <- ggplot(upperICV, aes(time_0)) +
  ggtitle("Time 0 - Upper ICV")+
  geom_histogram(aes(y=..density..), colour="black", fill="white",bins=7) + 
  labs(x = "Time (ms)", y = "Density")  +         
  stat_function(fun = dnorm,
                args = list(mean = mean(upperICV$time_0, na.rm = TRUE),
                            sd = sd(upperICV$time_0, na.rm = TRUE)), colour = "red", size = 1)

hist.time_1 <- ggplot(upperICV, aes(time_1)) +
  ggtitle("Time 1 - Upper ICV")+
  geom_histogram(aes(y=..density..), colour="black", fill="white",bins=7) + 
  labs(x = "Time (ms)", y = "Density")  +         
  stat_function(fun = dnorm,
                args = list(mean = mean(upperICV$time_1, na.rm = TRUE),
                            sd = sd(upperICV$time_1, na.rm = TRUE)), colour = "red", size = 1)

hist.time_2 <- ggplot(upperICV, aes(time_2)) +
  ggtitle("Time 2 - Upper ICV")+
  geom_histogram(aes(y=..density..), colour="black", fill="white",bins=7) + 
  labs(x = "Time (ms)", y = "Density")  +         
  stat_function(fun = dnorm,
                args = list(mean = mean(upperICV$time_2, na.rm = TRUE),
                            sd = sd(upperICV$time_2, na.rm = TRUE)), colour = "red", size = 1)

hist.time_3 <- ggplot(upperICV, aes(time_3)) +
  ggtitle("Time 3 - Upper ICV")+
  geom_histogram(aes(y=..density..), colour="black", fill="white",bins=7) + 
  labs(x = "Time (ms)", y = "Density")  +         
  stat_function(fun = dnorm,
                args = list(mean = mean(upperICV$time_3, na.rm = TRUE),
                            sd = sd(upperICV$time_3, na.rm = TRUE)), colour = "red", size = 1)

hist.times.upperICV = arrangeGrob(hist.time_0, hist.time_1, hist.time_2, hist.time_3 ,ncol=2,nrow=2)
grid.draw(hist.times.upperICV) # interactive device
ggsave("hist.times.upperICV.pdf", hist.times.upperICV) # need to specify what to save explicitly

# Visually check score distribution in across weeks in lower ICV
hist.score_0 <- ggplot(lowerICV, aes(score_0)) +
  ggtitle("Score 0 - Lower ICV")+
  geom_histogram(aes(y=..density..), colour="black", fill="white",bins=7) + 
  labs(x = "Score", y = "Density")  +         
  stat_function(fun = dnorm,
                args = list(mean = mean(lowerICV$score_0, na.rm = TRUE),
                            sd = sd(lowerICV$score_0, na.rm = TRUE)), colour = "red", size = 1)

hist.score_1 <- ggplot(lowerICV, aes(score_1)) +
  ggtitle("Score 1 - Lower ICV")+
  geom_histogram(aes(y=..density..), colour="black", fill="white",bins=7) + 
  labs(x = "Score", y = "Density")  +         
  stat_function(fun = dnorm,
                args = list(mean = mean(lowerICV$score_1, na.rm = TRUE),
                            sd = sd(lowerICV$score_1, na.rm = TRUE)), colour = "red", size = 1)

hist.score_2 <- ggplot(lowerICV, aes(score_2)) +
  ggtitle("Score 2 - Lower ICV")+
  geom_histogram(aes(y=..density..), colour="black", fill="white",bins=7) + 
  labs(x = "Score", y = "Density")  +         
  stat_function(fun = dnorm,
                args = list(mean = mean(lowerICV$score_2, na.rm = TRUE),
                            sd = sd(lowerICV$score_2, na.rm = TRUE)), colour = "red", size = 1)

hist.score_3 <- ggplot(lowerICV, aes(score_3)) +
  ggtitle("Score 3 - Lower ICV")+
  geom_histogram(aes(y=..density..), colour="black", fill="white",bins=7) + 
  labs(x = "Score", y = "Density")  +         
  stat_function(fun = dnorm,
                args = list(mean = mean(lowerICV$score_3, na.rm = TRUE),
                            sd = sd(lowerICV$score_3, na.rm = TRUE)), colour = "red", size = 1)

hist.scores.lowerICV = arrangeGrob(hist.score_0, hist.score_1, hist.score_2, hist.score_3 ,ncol=2,nrow=2)
grid.draw(hist.scores.lowerICV) # interactive device
ggsave("hist.scores.lowerICV.pdf", hist.scores.lowerICV) # need to specify what to save explicitly

# Visually check score distribution in across weeks in upper ICV
hist.score_0 <- ggplot(upperICV, aes(score_0)) +
  ggtitle("Score 0 - Upper ICV")+
  geom_histogram(aes(y=..density..), colour="black", fill="white",bins=7) + 
  labs(x = "Score", y = "Density")  +         
  stat_function(fun = dnorm,
                args = list(mean = mean(upperICV$score_0, na.rm = TRUE),
                            sd = sd(upperICV$score_0, na.rm = TRUE)), colour = "red", size = 1)

hist.score_1 <- ggplot(upperICV, aes(score_1)) +
  ggtitle("Score 1 - Upper ICV")+
  geom_histogram(aes(y=..density..), colour="black", fill="white",bins=7) + 
  labs(x = "Score", y = "Density")  +         
  stat_function(fun = dnorm,
                args = list(mean = mean(upperICV$score_1, na.rm = TRUE),
                            sd = sd(upperICV$score_1, na.rm = TRUE)), colour = "red", size = 1)

hist.score_2 <- ggplot(upperICV, aes(score_2)) +
  ggtitle("Score 2 - Upper ICV")+
  geom_histogram(aes(y=..density..), colour="black", fill="white",bins=7) + 
  labs(x = "Score", y = "Density")  +         
  stat_function(fun = dnorm,
                args = list(mean = mean(upperICV$score_2, na.rm = TRUE),
                            sd = sd(upperICV$score_2, na.rm = TRUE)), colour = "red", size = 1)

hist.score_3 <- ggplot(upperICV, aes(score_3)) +
  ggtitle("Score 3 - Upper ICV")+
  geom_histogram(aes(y=..density..), colour="black", fill="white",bins=7) + 
  labs(x = "Score", y = "Density")  +         
  stat_function(fun = dnorm,
                args = list(mean = mean(upperICV$score_3, na.rm = TRUE),
                            sd = sd(upperICV$score_3, na.rm = TRUE)), colour = "red", size = 1)

hist.scores.upperICV = arrangeGrob(hist.score_0, hist.score_1, hist.score_2, hist.score_3 ,ncol=2,nrow=2)
grid.draw(hist.scores.upperICV) # interactive device
ggsave("hist.scores.upperICV.pdf", hist.scores.upperICV) # need to specify what to save explicitly

#------------------------------OUTLIER SEARCH------------------------------

# Search for outliers in datasets hat significantly swayed away from normal distribution
# according to SW testing

# Slightly abnormal dataset: lowerICV, time_0, SW=0.95, p=0.04
by(noFO$time_0, noFO$QuantileICV, stat.desc, norm=T)

boxplot(noFO$time_0 ~ noFO$ICV, data = noFO, xlab = "ICV",
        ylab = "Time 0", main = "Boxplot - ICV(Time 0)")

outliersLowerICVtime_0 = boxplot(noFO$time_0 ~ noFO$ICV, data = noFO, xlab = "ICV",
                                 ylab = "Time 0", main = "Boxplot - ICV(Time 0)")$out

outliersLowerICVtime_0

# noFO$time_0 = ifelse(noFO$time_0==54, NA, noFO$time_0) - removing this outlier makes even more
# abnormal distribution, deciding to leave it on

# Abnormal dataset: lowerICV, time_3, SW=0.91, p=0.02
by(noFO$time_3, noFO$QuantileICV, stat.desc, norm=T)

boxplot(noFO$time_3 ~ noFO$ICV, data = noFO, xlab = "ICV",
        ylab = "Time 3", main = "Boxplot - ICV(Time 3)")

outliersUpperICVtime_3 = boxplot(noFO$time_3 ~ noFO$ICV, data = noFO, xlab = "ICV",
                                 ylab = "Time 3", main = "Boxplot - ICV(Time 3)")$out

outliersUpperICVtime_3

# noFO$time_3 = ifelse(noFO$time_3==54, NA, noFO$time_3)

# Removing the outlier changes SW's p from 0.02 to 0.03, but causes a major issue of
# other time values missing for ANOVA and standard residuals for regression model 2.
# Deciding to leave it in because this 1 outlier takes with itself another 3 data points.
# Significance increase definitely not worth it.
by(noFO$time_3, noFO$QuantileICV, stat.desc, norm=T)

#------------------------------CHANGING DATA TO LONG FORMAT + LONG SUBSETS------------------------------

# Reshape data to long format
head(noFO)
noFOLong = melt(noFO, id.vars=c("ID", "ICV", "QuantileICV", "IQ", "QuantileIQ"))

# Inspect to confirm melt worked
head(noFOLong)
tail(noFOLong)

# Subset scores from long data
noFOLongScores = subset(noFOLong, noFOLong$variable=="score_0" | noFOLong$variable=="score_1" 
                        | noFOLong$variable=="score_2" | noFOLong$variable=="score_3")

# Subset times from long data
noFOLongTimes = subset(noFOLong, noFOLong$variable=="time_0" | noFOLong$variable=="time_1" 
                       | noFOLong$variable=="time_2" | noFOLong$variable=="time_3")

# Subset both scores and times from long data
noFOLongScoresAndTimes = subset(noFOLong, noFOLong$variable=="score_0" | noFOLong$variable=="score_1" 
                                | noFOLong$variable=="score_2" | noFOLong$variable=="score_3" 
                                | noFOLong$variable=="time_0" | noFOLong$variable=="time_1" 
                                | noFOLong$variable=="time_2" | noFOLong$variable=="time_3")

# Subset RSIs from long data
noFOLongRSIs = subset(noFOLong, noFOLong$variable=="RSI1" | noFOLong$variable=="RSI2" 
                      | noFOLong$variable=="RSI3" )

# Subset RTIS from long data
noFOLongRTIs = subset(noFOLong, noFOLong$variable=="RTI1" | noFOLong$variable=="RTI2" 
                      | noFOLong$variable=="RTI3" )

# Create a custom column in LongScores that evaluates ICV and IQ quantiles at once

noFOLongScores$BothQuantiles1 = ifelse(noFOLongScores$QuantileICV=="upper", "Upper ICV, ", "Lower ICV, ")
noFOLongScores$BothQuantiles2 = ifelse(noFOLongScores$QuantileIQ=="upper", "Upper IQ", "Lower IQ")
head(noFOLongScores)
noFOLongScores$ICV_IQ = paste(noFOLongScores$BothQuantiles1, noFOLongScores$BothQuantiles2)
head(noFOLongScores)

# Create a custom column in LongTimes that evaluates ICV and IQ quantiles at once

noFOLongTimes$BothQuantiles1 = ifelse(noFOLongTimes$QuantileICV=="upper", "Upper ICV, ", "Lower ICV, ")
noFOLongTimes$BothQuantiles2 = ifelse(noFOLongTimes$QuantileIQ=="upper", "Upper IQ", "Lower IQ")
head(noFOLongTimes)
noFOLongTimes$ICV_IQ = paste(noFOLongTimes$BothQuantiles1, noFOLongTimes$BothQuantiles2)
head(noFOLongTimes)

# Create a custom column in LongRSIs that evaluates ICV and IQ quantiles at once

noFOLongRSIs$BothQuantiles1 = ifelse(noFOLongRSIs$QuantileICV=="upper", "Upper ICV, ", "Lower ICV, ")
noFOLongRSIs$BothQuantiles2 = ifelse(noFOLongRSIs$QuantileIQ=="upper", "Upper IQ", "Lower IQ")
head(noFOLongRSIs)
noFOLongRSIs$ICV_IQ = paste(noFOLongRSIs$BothQuantiles1, noFOLongRSIs$BothQuantiles2)
head(noFOLongRSIs)

# Create a custom column in LongRTIs that evaluates ICV and IQ quantiles at once

noFOLongRTIs$BothQuantiles1 = ifelse(noFOLongRTIs$QuantileICV=="upper", "Upper ICV, ", "Lower ICV, ")
noFOLongRTIs$BothQuantiles2 = ifelse(noFOLongRTIs$QuantileIQ=="upper", "Upper IQ", "Lower IQ")
head(noFOLongRTIs)
noFOLongRTIs$ICV_IQ = paste(noFOLongRTIs$BothQuantiles1, noFOLongRTIs$BothQuantiles2)
head(noFOLongRTIs)

#------------------------------BOX PLOTS------------------------------

# Make a boxplot of scores across ICV and IQ quantiles

allScoresBothQuantilesBoxPlot = ggplot(noFOLongScores, aes(x=variable, y=value, fill=ICV_IQ)) +
  geom_boxplot() + labs(x="", y="Score") +
  ggtitle("Boxplot - Scores across ICV and IQ quantiles") +
  theme(legend.position = "bottom", legend.direction = "vertical")
allScoresBothQuantilesBoxPlot
ggsave("allScoresBothQuantilesBoxPlot.pdf", allScoresBothQuantilesBoxPlot) # need to specify what to save explicitly

# Make a boxplot of times across ICV and IQ quantiles

allTimesBothQuantilesBoxPlot = ggplot(noFOLongTimes, aes(x=variable, y=value, fill=ICV_IQ)) +
  geom_boxplot() + labs(x="", y="Time") +
  ggtitle("Boxplot - Times across ICV and IQ quantiles") +
  theme(legend.position = "bottom", legend.direction = "vertical")
allTimesBothQuantilesBoxPlot
ggsave("allTimesBothQuantilesBoxPlot.pdf", allTimesBothQuantilesBoxPlot) # need to specify what to save explicitly


#------------------------------CORRELATION TESTING------------------------------

# Calculate Spearman's correlation between ICV and scores
# S=4455377, p=0.86, rho=0.0099
cor.test(noFOLongScores$ICV, noFOLongScores$value, alternative="two.sided", method="spearman", conf.level=.95)

# Calculate Spearman's correlation between ICV and times
# S=4667908, p=0.52, rho=-0.0373
cor.test(noFOLongTimes$ICV, noFOLongTimes$value, alternative="two.sided", method="spearman", conf.level=.95)

# Calculate Spearman's correlation between IQ and scores
### S=2806498, p=1.58e-11, rho=0.3763
cor.test(noFOLongScores$IQ, noFOLongScores$value, alternative="two.sided", method="spearman", conf.level=.95)

# Calculate Spearman's correlation between IQ and times
### S=5601870, p=1.52e-05, rho=-0.2449
cor.test(noFOLongTimes$IQ, noFOLongTimes$value, alternative="two.sided", method="spearman", conf.level=.95)

#------------------------------PARTIAL CORRELATION USING RSI AND RTI------------------------------

# Instead of partial correlation corrected for score_0 or time_0, calculate correlations for
# RSIs and RTIs, as they are already corrected/relative to baseline values

# Calculate Spearman's correlation between ICV and RSIs
# S=1962548, p=0.69, rho=-0.3380
cor.test(noFOLongRSIs$ICV, noFOLongRSIs$value, alternative="greater", method="spearman", conf.level=.95)

# Calculate Spearman's correlation between ICV and RTIs
# S=1868178, p=0.59, rho=0.0159
cor.test(noFOLongRTIs$ICV, noFOLongRTIs$value, alternative="less", method="spearman", conf.level=.95)

# Calculate Spearman's correlation between IQ and RSIs
# S=2156112, p=0.98, rho=-0.1357
cor.test(noFOLongRSIs$IQ, noFOLongRSIs$value, alternative="greater", method="spearman", conf.level=.95)

# Calculate Spearman's correlation between IQ and RTIs
# S=1998348, p=0.22, rho=-0.0526
cor.test(noFOLongRTIs$IQ, noFOLongRTIs$value, alternative="less", method="spearman", conf.level=.95)

#------------------------------SCATTERPLOTS OF SIGNIFICANT CORRELATION------------------------------

# Scatterplots of data that had significant correlation

# Scatterplot of IQ and scores
### S=2806498, p=1.58e-11, rho=0.3763
# Make a scatterplot of scores across IQ
scatterplotScoresIQ = ggplot(noFOLongScores,aes(IQ, value, colour=variable)) +
  ggtitle("Scatterplot - IQ(All Scores)") +
  geom_point(shape=19, size=2) + labs(x="IQ", y="Score") +
  geom_vline(xintercept=107.5, linetype='dashed', color='red', size=0.5) +
  xlim(79,135) + ylim(70,96) +
  geom_smooth(method = "lm", formula = y ~ x, color='blue')
scatterplotScoresIQ
ggsave("scatterplotScoresIQ.pdf", scatterplotScoresIQ) # save image file

# Scatterplot of IQ and times
### S=5557024, p=1.52e-05, rho=-0.2473
# Make a scatterplot of times across IQ
scatterplotTimesIQ = ggplot(noFOLongTimes,aes(IQ, value, colour=variable)) +
  ggtitle("Scatterplot - IQ(All times)") +
  geom_point(shape=19, size=2) + labs(x="IQ", y="Time") +
  geom_vline(xintercept=107.5, linetype='dashed', color='red', size=0.5) +
  xlim(79,135) + ylim(40,58) +
  geom_smooth(method = "lm", formula = y ~ x, color='blue')
scatterplotTimesIQ
ggsave("scatterplotTimesIQ.pdf", scatterplotTimesIQ) # save image file

#------------------------------REGRESSION MODELS WITH SIGNIFICANT CORRELATION------------------------------

# Create first regression model - score (DV) and IQ (IV)
regressionScoresIQ = lm(value ~ IQ, data=noFOLongScores, na.action=na.exclude)

# Beta=0.16, t=7.39, p=1.5e-12
# F(1,298)=54.56, p=1.5e-12, adj R2=0.1519
# Model 1 looks like a better fit, as it is much more significant
# and has a higher R2 value, high F statistic
# suggests lower unsystematic variance
summary(regressionScoresIQ)

noFOLongScores$model1Fitted = regressionScoresIQ$fitted.values     	# predicted values
noFOLongScores$model1Resid = regressionScoresIQ$residuals           # raw residuals 
noFOLongScores$model1StandResid = rstandard(regressionScoresIQ)   	# standardized residuals
head(noFOLongScores)

# Create second regression model - time (DV) and IQ (IV)
regressionTimesIQ = lm(value ~ IQ, data=noFOLongTimes, na.action=na.exclude)

# Beta=-0.08, t=-4.88, p=1.75e-06
# F(1,298)=23.79, p=1.75e-06, adj R2=0.07082
# Everything is worse here pretty much, lower significance, lower R2 and
# lower F statistic suggesting a higher unsystematic variance
summary(regressionTimesIQ)

noFOLongTimes$model2Fitted = regressionTimesIQ$fitted.values     	# predicted values
noFOLongTimes$model2Resid = regressionTimesIQ$residuals           # raw residuals 
noFOLongTimes$model2StandResid = rstandard(regressionTimesIQ)   	# standardized residuals
head(noFOLongTimes)

#------------------------------REGRESSION ASSUMPTIONS MODEL 1------------------------------

# Standardized residuals versus observed values IQ (heteroscedasticity)

# BP(1)=0.91, p=0.34
bptest(regressionScoresIQ)

model1Heterosc = ggplot(noFOLongScores, aes(IQ, model1StandResid)) +
  geom_point() + labs(x="Observed IQ values", y="Standardized residuals") +
  geom_smooth(method="lm", colour="Blue")

# Standardized residuals versus ID (independence)

# DW=1.67, p=0.0044
dwtest(regressionScoresIQ, alternative="two.sided")

model1Indep = ggplot(noFOLongScores, aes(ID, model1StandResid)) +
  geom_point() + labs(x="Subject ID", y="Standardized residuals") +
  geom_smooth(method="lm", colour="Blue")

# Distribution of standardized residuals (normality)

# W=0.99, p=0.26
stat.desc(noFOLongScores$model1StandResid, basic=F, norm=T)

model1Norm = ggplot(noFOLongScores, aes(model1StandResid)) + 
  geom_histogram(aes(y=..density..), colour="black", fill="white", bins=7) + 
  labs(x = "Residuals", y = "Density")  +         
  stat_function(fun = dnorm, args = list(mean = mean(noFOLongScores$model1StandResid, na.rm = TRUE), sd = sd(noFOLongScores$model1StandResid, na.rm = TRUE)), colour = "red", size = 1)

model1Assumptions = arrangeGrob(model1Heterosc, model1Indep, model1Norm, ncol=3, nrow=1)
grid.draw(model1Assumptions) # interactive device
ggsave("model1Assumptions.pdf", model1Assumptions) # save image file

#------------------------------REGRESSION ASSUMPTIONS MODEL 2------------------------------

# Standardized residuals versus observed values IQ (heteroscedasticity)

# BP(1)=0.68, p=0.41
bptest(regressionTimesIQ)

model2Heterosc = ggplot(noFOLongTimes, aes(IQ, model2StandResid)) +
  geom_point() + labs(x="Observed IQ values", y="Standardized residuals") +
  geom_smooth(method="lm", colour="Blue")

# Standardized residuals versus ID (independence)

# DW=1.38, p=6.56e-08
dwtest(regressionTimesIQ, alternative="two.sided")

model2Indep = ggplot(noFOLongTimes, aes(ID, model2StandResid)) +
  geom_point() + labs(x="Subject ID", y="Standardized residuals") +
  geom_smooth(method="lm", colour="Blue")

# Distribution of standardized residuals (normality)

# W=0.99, p=0.38
stat.desc(noFOLongTimes$model2StandResid, basic=F, norm=T)

model2Norm = ggplot(noFOLongTimes, aes(model2StandResid)) + 
  geom_histogram(aes(y=..density..), colour="black", fill="white", bins=7) + 
  labs(x = "Residuals", y = "Density")  +         
  stat_function(fun = dnorm, args = list(mean = mean(noFOLongTimes$model2StandResid, na.rm = TRUE), sd = sd(noFOLongTimes$model2StandResid, na.rm = TRUE)), colour = "red", size = 1)

model2Assumptions = arrangeGrob(model2Heterosc, model2Indep, model2Norm, ncol=3, nrow=1)
grid.draw(model2Assumptions) # interactive device
ggsave("model2Assumptions.pdf", model2Assumptions) # save image file

#------------------------------RESIDUALS WITHIN Z SCORE RANGES------------------------------

# Check how many standardized residuals are within the ranges
# of |3.3|, |2.58|, and |1.96|

sampleSize = 75

# Model 1
stResMod1V = as.vector(noFOLongScores$model1StandResid)
nGreaterThanAbs3.3 = length(c(stResMod1V[stResMod1V< -3.3], stResMod1V[stResMod1V>3.3]))	# > |3.3|
nGreaterThanAbs2.58 = length(c(stResMod1V[stResMod1V< -2.58], stResMod1V[stResMod1V>2.58]))	# > |2.58|
nGreaterThanAbs1.96 = length(c(stResMod1V[stResMod1V< -1.96], stResMod1V[stResMod1V>1.96]))	# > |1.96|

# 0% > |3.3|
(nGreaterThanAbs3.3/sampleSize) * 100
# 2.67% > |2.58|
(nGreaterThanAbs2.58/sampleSize) * 100
# 14.67% > |1.96|
(nGreaterThanAbs1.96/sampleSize) * 100

# Model 2
stResMod1V = as.vector(noFOLongTimes$model2StandResid)
nGreaterThanAbs3.3 = length(c(stResMod1V[stResMod1V< -3.3], stResMod1V[stResMod1V>3.3]))	# > |3.3|
nGreaterThanAbs2.58 = length(c(stResMod1V[stResMod1V< -2.58], stResMod1V[stResMod1V>2.58]))	# > |2.58|
nGreaterThanAbs1.96 = length(c(stResMod1V[stResMod1V< -1.96], stResMod1V[stResMod1V>1.96]))	# > |1.96|

# 0% > |3.3|
(nGreaterThanAbs3.3/sampleSize) * 100
# 2.67% > |2.58|
(nGreaterThanAbs2.58/sampleSize) * 100
# 14.67% > |1.96|
(nGreaterThanAbs1.96/sampleSize) * 100

#------------------------------LEVENE TESTS FOR EQUAL VARIANCES------------------------------

# Levene test for equal score variances among lower ICV quantile
# F(1,298)=1.40, p=0.24
leveneTest(noFOLongScores$value, noFOLongScores$ICV & noFOLongScores$QuantileICV=="lower", center=mean)

# Levene test for equal score variances among upper ICV quantile
# F(1,298)=1.40, p=0.24
leveneTest(noFOLongScores$value, noFOLongScores$ICV & noFOLongScores$QuantileICV=="upper", center=mean)

# Levene test for equal time variances among lower ICV quantile
# F(1,298)=2.03, p=0.15
leveneTest(noFOLongTimes$value, noFOLongTimes$ICV & noFOLongTimes$QuantileICV=="lower", center=mean)

# Levene test for equal time variances among upper ICV quantile
# F(1,298)=2.03, p=0.15
leveneTest(noFOLongTimes$value, noFOLongTimes$ICV & noFOLongTimes$QuantileICV=="upper", center=mean)

# Levene test for equal score variances among lower IQ quantile
# F(1,298)=0.89, p=0.35
leveneTest(noFOLongScores$value, noFOLongScores$IQ & noFOLongScores$QuantileIQ=="lower", center=mean)

# Levene test for equal score variances among upper IQ quantile
# F(1,298)=0.89, p=0.35
leveneTest(noFOLongScores$value, noFOLongScores$IQ & noFOLongScores$QuantileIQ=="upper", center=mean)

# Levene test for equal time variances among lower IQ quantile
# F(1,298)=0.50, p=0.48
leveneTest(noFOLongTimes$value, noFOLongTimes$IQ & noFOLongTimes$QuantileIQ=="lower", center=mean)

# Levene test for equal time variances among upper IQ quantile
# F(1,298)=0.50, p=0.48
leveneTest(noFOLongTimes$value, noFOLongTimes$IQ & noFOLongTimes$QuantileIQ=="upper", center=mean)

#------------------------------INDEPENDENT T-TESTS, ICV GROUPS------------------------------

# Ho: Score means are not different between lower and upper ICV
# Ha: Score means are different between lower and upper ICV

# t(298)=-0.29, p=0.77, Ho accepted
tTestScoreUpperLowerICV = t.test(noFOLongScores$value ~ noFOLongScores$QuantileICV,
      data=noFOLongScores, alternative=c("two.sided"), var.equal=T, paired=F)
tTestScoreUpperLowerICV

# Ho: Time means are not different between lower and upper ICV
# Ha: Time means are different between lower and upper ICV

# t(298)=1.52, p=0.13, Ho accepted
tTestTimeUpperLowerICV = t.test(noFOLongTimes$value ~ noFOLongTimes$QuantileICV,
      data=noFOLongTimes, alternative=c("two.sided"), var.equal=T, paired=F)
tTestTimeUpperLowerICV

#------------------------------INDEPENDENT T-TESTS, IQ GROUPS------------------------------

# Ho: Score means are not different between lower and upper IQ
# Ha: Score means are different between lower and upper IQ

# t(298)=-7.44, p=1.08e-12, Ho rejected
tTestScoreUpperLowerIQ = t.test(noFOLongScores$value ~ noFOLongScores$QuantileIQ,
       data=noFOLongScores, alternative=c("two.sided"), var.equal=T, paired=F)
tTestScoreUpperLowerIQ

# Ho: Time means are not different between lower and upper IQ
# Ha: Time means are different between lower and upper IQ

# t(298)=3.56, p=4.3e-04, Ho rejected
tTestTimeUpperLowerIQ = t.test(noFOLongTimes$value ~ noFOLongTimes$QuantileIQ,
       data=noFOLongTimes, alternative=c("two.sided"), var.equal=T, paired=F)
tTestTimeUpperLowerIQ

#------------------------------EFFECT SIZE OF IQ ON SCORE_0------------------------------

# Create score_0 ranks
noFO$score_0Ranks = rank(noFO$score_0)

# Score ranks sum lowerIQ:  1422 - smallest sum
# Score ranks sum upperIQ:  1428
by(noFO$score_0Ranks, noFO$QuantileIQ, stat.desc)

# 48 score measurements for lowerIQ, 27 measurements for upperIQ
table(noFO$QuantileIQ)

Ws = 1422
Nl = 48
Nu = 27
Wsmean <- (Nl*(Nl+Nu+1))/2
SEws <- sqrt((Nl*Nu*(Nl+Nu+1))/12)
Z <- (Ws-Wsmean)/SEws
r <- Z/sqrt(Nl+Nu)
r # -0.5124

#------------------------------EFFECT SIZE OF IQ ON SCORE_1------------------------------

# Create score_1 ranks
noFO$score_1Ranks = rank(noFO$score_1)

# Score ranks sum lowerIQ:  1517
# Score ranks sum upperIQ:  1332
by(noFO$score_1Ranks , noFO$QuantileIQ, stat.desc)

# 48 score measurements for lowerIQ, 27 measurements for upperIQ
table(noFO$QuantileIQ)

Ws = 1332
Nl = 48
Nu = 27
Wsmean <- (Nl*(Nl+Nu+1))/2
SEws <- sqrt((Nl*Nu*(Nl+Nu+1))/12)
Z <- (Ws-Wsmean)/SEws
r <- Z/sqrt(Nl+Nu)
r # -0.6271

#------------------------------EFFECT SIZE OF IQ ON SCORE_2------------------------------

# Create score_2 ranks
noFO$score_2Ranks = rank(noFO$score_2)

# Score ranks sum lowerIQ:  1541
# Score ranks sum upperIQ:  1309 - smallest sum
by(noFO$score_2Ranks , noFO$QuantileIQ, stat.desc)

# 48 score measurements for lowerIQ, 27 measurements for upperIQ
table(noFO$QuantileIQ)

Ws = 1309
Nl = 48
Nu = 27
Wsmean <- (Nl*(Nl+Nu+1))/2
SEws <- sqrt((Nl*Nu*(Nl+Nu+1))/12)
Z <- (Ws-Wsmean)/SEws
r <- Z/sqrt(Nl+Nu)
r # -0.6564

#------------------------------EFFECT SIZE OF IQ ON SCORE_3------------------------------

# Create score_3 ranks
noFO$score_3Ranks = rank(noFO$score_3)

# Score ranks sum lowerIQ:  1496
# Score ranks sum upperIQ:  1353 - smallest sum
by(noFO$score_3Ranks , noFO$QuantileIQ, stat.desc)

# 48 score measurements for lowerIQ, 27 measurements for upperIQ
table(noFO$QuantileIQ)

Ws = 1353
Nl = 48
Nu = 27
Wsmean <- (Nl*(Nl+Nu+1))/2
SEws <- sqrt((Nl*Nu*(Nl+Nu+1))/12)
Z <- (Ws-Wsmean)/SEws
r <- Z/sqrt(Nl+Nu)
r # -0.6003

#------------------------------EFFECT SIZE OF IQ ON TIME_0------------------------------

# Create time_0 ranks
noFO$time_0Ranks = rank(noFO$time_0)

# Time ranks sum lowerIQ:  2011
# Time ranks sum upperIQ:  839 - smallest sum
by(noFO$time_0Ranks , noFO$QuantileIQ, stat.desc)

# 48 time measurements for lowerIQ, 27 measurements for upperIQ
table(noFO$QuantileIQ)

Ws = 839
Nl = 48
Nu = 27
Wsmean <- (Nl*(Nl+Nu+1))/2
SEws <- sqrt((Nl*Nu*(Nl+Nu+1))/12)
Z <- (Ws-Wsmean)/SEws
r <- Z/sqrt(Nl+Nu)
r # -1.2554

#------------------------------EFFECT SIZE OF IQ ON TIME_1------------------------------

# Create time_1 ranks
noFO$time_1Ranks = rank(noFO$time_1)

# Time ranks sum lowerIQ:  2007
# Time ranks sum upperIQ:  843 - smallest sum
by(noFO$time_1Ranks , noFO$QuantileIQ, stat.desc)

# 48 time measurements for lowerIQ, 27 measurements for upperIQ
table(noFO$QuantileIQ)

Ws = 843
Nl = 48
Nu = 27
Wsmean <- (Nl*(Nl+Nu+1))/2
SEws <- sqrt((Nl*Nu*(Nl+Nu+1))/12)
Z <- (Ws-Wsmean)/SEws
r <- Z/sqrt(Nl+Nu)
r # -1.2503

#------------------------------EFFECT SIZE OF IQ ON TIME_2------------------------------

# Create time_2 ranks
noFO$time_2Ranks = rank(noFO$time_2)

# Time ranks sum lowerIQ:  1957
# Time ranks sum upperIQ:  893 - smallest sum
by(noFO$time_2Ranks , noFO$QuantileIQ, stat.desc)

# 48 time measurements for lowerIQ, 27 measurements for upperIQ
table(noFO$QuantileIQ)

Ws = 893
Nl = 48
Nu = 27
Wsmean <- (Nl*(Nl+Nu+1))/2
SEws <- sqrt((Nl*Nu*(Nl+Nu+1))/12)
Z <- (Ws-Wsmean)/SEws
r <- Z/sqrt(Nl+Nu)
r # -1.1866

#------------------------------EFFECT SIZE OF IQ ON TIME_3------------------------------

# Create time_3 ranks
noFO$time_3Ranks = rank(noFO$time_3)

# Time ranks sum lowerIQ:  1978
# Time ranks sum upperIQ:  872 - smallest sum
by(noFO$time_3Ranks , noFO$QuantileIQ, stat.desc)

# 48 time measurements for lowerIQ, 27 measurements for upperIQ
table(noFO$QuantileIQ)

Ws = 872
Nl = 48
Nu = 27
Wsmean <- (Nl*(Nl+Nu+1))/2
SEws <- sqrt((Nl*Nu*(Nl+Nu+1))/12)
Z <- (Ws-Wsmean)/SEws
r <- Z/sqrt(Nl+Nu)
r # -1.2133

#------------------------------PLOTTED TRENDS OF SCORES AND TIMES ACROSS ALL GROUPS------------------------------

trendScores = ggplot(noFOLongScores,aes(variable, value, colour=ICV_IQ)) +
  stat_summary(fun=mean,geom="point", size=2) +
  stat_summary(fun=mean, geom="line", size=1.25, aes(group=ICV_IQ)) +
  stat_summary(fun.data=mean_cl_boot, geom="errorbar", width=.2) +
  labs(x="Scoring Stage", y="Mean Score") +
  theme(legend.position = "bottom", legend.direction = "vertical") +
  trendScores
ggsave("trendScores.pdf", trendScores) # save image file

trendRSIs = ggplot(noFOLongRSIs,aes(variable, value, colour=ICV_IQ)) +
  stat_summary(fun=mean,geom="point", size=2) +
  stat_summary(fun=mean, geom="line", size=1.25, aes(group=ICV_IQ)) +
  stat_summary(fun.data=mean_cl_boot, geom="errorbar", width=.2) +
  theme(legend.position = "bottom", legend.direction = "vertical") +
  labs(x="RSI Stage", y="Mean RSI")
trendRSIs
ggsave("trendRSIs.pdf", trendRSIs) # save image file

trendTimes = ggplot(noFOLongTimes,aes(variable, value, colour=ICV_IQ)) +
  stat_summary(fun=mean,geom="point", size=2) +
  stat_summary(fun=mean, geom="line", size=1.25, aes(group=ICV_IQ)) +
  stat_summary(fun.data=mean_cl_boot, geom="errorbar", width=.2) +
  labs(x="Time Stage", y="Mean Time") +
  theme(legend.position = "right", legend.direction = "vertical")
trendTimes
ggsave("trendTimes.pdf", trendTimes) # save image file

trendRTIs = ggplot(noFOLongRTIs,aes(variable, value, colour=ICV_IQ)) +
  stat_summary(fun=mean,geom="point", size=2) +
  stat_summary(fun=mean, geom="line", size=1.25, aes(group=ICV_IQ)) +
  stat_summary(fun.data=mean_cl_boot, geom="errorbar", width=.2) +
  labs(x="RTI Stage", y="Mean RTI") +
  theme(legend.position = "bottom", legend.direction = "vertical")
trendRTIs
ggsave("trendRTIs.pdf", trendRTIs) # save image file

#------------------------------Repeated measures ANOVA - DIFFERENCES IN SCORES ACROSS ICV------------------------------

repmANOVAScoresICV = aov_ez("ID", "value", noFOLongScores, between = NULL, within = c("variable"), 
                 anova_table=list(correction = "GG", es = "pes"), print.formula=T, 
                 type=c("3"), check_contrasts=T,
                 return = c("afex_aov"))

# Mauchly W=0.93, p=0.42, no violation of Sphericity
# Ho: Familiarity with the test over time had no significant effect on means of test scores.
# Ha: Familiarity with the test over time had a significant effect on means of test scores.
# Ho rejected
# F(3,222)=109.09, p < 2.2e-16
summary(repmANOVAScoresICV)

#------------------------------Repeated measures ANOVA - DIFFERENCES IN RSIs across ICV------------------------------

repmANOVARSIsICV = aov_ez("ID", "value", noFOLongRSIs, between = NULL, within = c("variable"), 
                            anova_table=list(correction = "GG", es = "pes"), print.formula=T, 
                            type=c("3"), check_contrasts=T,
                            return = c("afex_aov"))

# Mauchly W=0.95, p=0.19, no violation of Sphericity
# Ho: Familiarity with the test over time had increased means of Relative Score Improvements.
# Ha: Familiarity with the test over time had not increased means of Relative Score Improvements.
# Ho rejected
# F(2, 148)=54.72, p < 2.2e-16 / 2
summary(repmANOVARSIsICV)

#------------------------------Repeated measures ANOVA - DIFFERENCES IN TIMES ACROSS ICV------------------------------

repmANOVATimesICV = aov_ez("ID", "value", noFOLongTimes, between = NULL, within = c("variable"), 
                            anova_table=list(correction = "HF", es = "pes"), print.formula=T, 
                            type=c("3"), check_contrasts=T,
                            return = c("afex_aov"))

# Mauchly W=0.83, p=0.02, violation of Sphericity - correct with EpsilonGG
# Ho: Familiarity with the test over time had no significant effect on time means to complete the test.
# Ha: Familiarity with the test over time had a significant effect on time means to complete the test.
# Ho rejected

# Initial:
# F(3,222)=130.04, p<2.2e-16
summary(repmANOVATimesICV)

Df1 = 2*0.88408
Df2 = 148*0.88408
Df1
Df2

# Greenhouse-Geisser (GG eps) corrected:
# F(1.77, 130.84)=130.04, p<2.2e-16


#------------------------------Repeated measures ANOVA - DIFFERENCES IN RTIs across ICV------------------------------

repmANOVARTIsICV = aov_ez("ID", "value", noFOLongRTIs, between = NULL, within = c("variable"), 
                          anova_table=list(correction = "GG", es = "pes"), print.formula=T, 
                          type=c("3"), check_contrasts=T,
                          return = c("afex_aov"))

# Mauchly W=0.96, p=0.20, no violation of Sphericity
# Ho: Familiarity with the test over time had increased means of Relative Score Improvements.
# Ha: Familiarity with the test over time had not increased means of Relative Score Improvements.
# Ho rejected
# F(2, 148)=56.21, p < 2.2e-16 / 2
summary(repmANOVARTIsICV)

#------------------------------MULTI-LEVEL ANALYSIS FOR SCORE DIFFERENCES IN BETWEEN IQ GROUPS------------------------------

repmANOVAScoresIQ = aov_ez("ID", "value", noFOLongScores, between = c("QuantileIQ"), within = c("variable"), 
                           anova_table=list(correction = "GG", es = "pes"), print.formula=T, 
                           type=c("3"), check_contrasts=T,
                           return = c("afex_aov"))

# Mauchly W=0.93, p=0.37, no violation of Sphericity
# Ho: There is no difference within score means in between lower and upper IQ.
# Ha: There is a difference within score means in between lower and upper IQ.
# Ho accepted
# F(3, 219)=1.17, p=0.32
summary(repmANOVAScoresIQ)

# Multilevel model

# Fit a means model (intercept only)
scoreDiffIQIntOnly = gls(value ~ 1, data=noFOLongScores, method="ML")
summary(scoreDiffIQIntOnly)

# Fit a random means model (moving intercept for score means across IQ quantiles)
scoreDiffIQRandomIntOnly = lme(value ~ 1, data=noFOLongScores, random = ~1|QuantileIQ, method="ML")
summary(scoreDiffIQRandomIntOnly)

# L(3)=42.39, p<0.0001, lower AID, BIC and logLik values in model 1 vs model 2
anova(scoreDiffIQIntOnly, scoreDiffIQRandomIntOnly)

t.test(value ~ QuantileIQ, data=noFOLongScores, alternative="two.sided", var.equal=T, paired=F)
aggr = aggregate(noFOLongScores, by=list(noFOLongScores$ID, noFOLongScores$QuantileIQ), FUN=mean, na.rm=TRUE)

# Ho: There is no difference within score means in between lower and upper IQ.
# Ha: There is a difference within score means in between lower and upper IQ.
# Ho rejected
# t(73)=-4.56, p=2.02e-05
t.test(value ~ Group.2, data=aggr, alternative="two.sided", var.equal=T, paired=F)

#------------------------------MULTI-LEVEL ANALYSIS FOR RSI DIFFERENCES IN BETWEEN IQ GROUPS------------------------------

repmANOVARSIsIQ = aov_ez("ID", "value", noFOLongRSIs, between = c("QuantileIQ"), within = c("variable"), 
                           anova_table=list(correction = "GG", es = "pes"), print.formula=T, 
                           type=c("3"), check_contrasts=T,
                           return = c("afex_aov"))

# Mauchly W=0.93, p=0.37, no violation of Sphericity
# Ho: There is no increase within mean RSI in between lower and upper IQ.
# Ha: There is an increase within mean RSI in between lower and upper IQ.
# Ho accepted
# F(2, 146)=0.67, p=0.51 / 2
summary(repmANOVARSIsIQ)

# Multilevel model

# Fit a means model (intercept only)
rsiDiffIQIntOnly = gls(value ~ 1, data=noFOLongRSIs, method="ML")
summary(rsiDiffIQIntOnly)

# Fit a random means model (moving intercept for score means across IQ quantiles)
rsiDiffIQRandomIntOnly = lme(value ~ 1, data=noFOLongRSIs, random = ~1|QuantileIQ, method="ML")
summary(rsiDiffIQRandomIntOnly)

# L(3)=1.37, p=0.24, lower logLik values in model 1 vs model 2, not a great improvement
anova(rsiDiffIQIntOnly, rsiDiffIQRandomIntOnly)
t.test(value ~ QuantileIQ, data=noFOLongRSIs, alternative="two.sided", var.equal=T, paired=F)
aggr = aggregate(noFOLongRSIs, by=list(noFOLongRSIs$ID, noFOLongRSIs$QuantileIQ), FUN=mean, na.rm=TRUE)

# Ho: There is no increase within mean RSI in between lower and upper IQ.
# Ha: There is an increase within mean RSI in between lower and upper IQ.
# Ho rejected
# t(73)=1.73, p=0.04
t.test(value ~ Group.2, data=aggr, alternative="greater", var.equal=T, paired=F)

#------------------------------MULTI-LEVEL ANALYSIS FOR TIME DIFFERENCES IN BETWEEN IQ GROUPS------------------------------

repmANOVATimesIQ = aov_ez("ID", "value", noFOLongTimes, between = c("QuantileIQ"), within = c("variable"), 
                           anova_table=list(correction = "GG", es = "pes"), print.formula=T, 
                           type=c("3"), check_contrasts=T,
                           return = c("afex_aov"))

# Mauchly W=0.83, p=0.02, violation of Sphericity, correct with Epsilon GG
# Ho: There is no difference within Time means in between lower and upper IQ.
# Ha: There is a difference within Time means in between lower and upper IQ.
# Ho accepted

# Initial:
# F(3,219)=0.07, p=0.98
summary(repmANOVATimesIQ)

Df1 = 3*0.88404
Df2 = 219*0.88404
Df1
Df2

# Greenhouse-Geisser (GG eps) corrected:
# F(2.65, 193.60)=130.04, p=0.97

# Multilevel model

# Fit a means model (intercept only)
TimeDiffIQIntOnly = gls(value ~ 1, data=noFOLongTimes, method="ML")
summary(TimeDiffIQIntOnly)

# Fit a random means model (moving intercept for Time means across IQ quantiles)
TimeDiffIQRandomIntOnly = lme(value ~ 1, data=noFOLongTimes, random = ~1|QuantileIQ, method="ML")
summary(TimeDiffIQRandomIntOnly)

# L(3)=6.71, p=0.01, lower AID, BIC and logLik values in model 1 vs model 2
anova(TimeDiffIQIntOnly, TimeDiffIQRandomIntOnly)
t.test(value ~ QuantileIQ, data=noFOLongTimes, alternative="two.sided", var.equal=T, paired=F)
aggr = aggregate(noFOLongTimes, by=list(noFOLongTimes$ID, noFOLongTimes$QuantileIQ), FUN=mean, na.rm=TRUE)

# Ho: There is no difference within Time means in between lower and upper IQ.
# Ha: There is a difference within Time means in between lower and upper IQ.
# Ho rejected
# t(73)=2.37, p=0.02
t.test(value ~ Group.2, data=aggr, alternative="two.sided", var.equal=T, paired=F)

#------------------------------MULTI-LEVEL ANALYSIS FOR RTI DIFFERENCES IN BETWEEN IQ GROUPS------------------------------

repmANOVARTIsIQ = aov_ez("ID", "value", noFOLongRTIs, between = c("QuantileIQ"), within = c("variable"), 
                         anova_table=list(correction = "GG", es = "pes"), print.formula=T, 
                         type=c("3"), check_contrasts=T,
                         return = c("afex_aov"))

# Mauchly W=0.96, p=0.20, no violation of Sphericity
# Ho: There is no decrease within mean RTI in between lower and upper IQ.
# Ha: There is a decrease within mean RTI in between lower and upper IQ.
# Ho accepted
# F(2, 146)=0.06, p=0.94 / 2
summary(repmANOVARTIsIQ)

# Multilevel model

# Fit a means model (intercept only)
RTIDiffIQIntOnly = gls(value ~ 1, data=noFOLongRTIs, method="ML")
summary(RTIDiffIQIntOnly)

# Fit a random means model (moving intercept for score means across IQ quantiles)
RTIDiffIQRandomIntOnly = lme(value ~ 1, data=noFOLongRTIs, random = ~1|QuantileIQ, method="ML")
summary(RTIDiffIQRandomIntOnly)

# L(3)=1.54e-07, p=1, , same AIC, BIC and logLik values, no improvement at all
anova(RTIDiffIQIntOnly, RTIDiffIQRandomIntOnly)
t.test(value ~ QuantileIQ, data=noFOLongRTIs, alternative="two.sided", var.equal=T, paired=F)
aggr = aggregate(noFOLongRTIs, by=list(noFOLongRTIs$ID, noFOLongRTIs$QuantileIQ), FUN=mean, na.rm=TRUE)

# Ho: There is no increase within mean RTI in between lower and upper IQ.
# Ha: There is an increase withim mean RTI in between lower and upper IQ.
# Ho accepted
# t(73)=0.04, p=0.51
t.test(value ~ Group.2, data=aggr, alternative="less", var.equal=T, paired=F)

#------------------------------EXTRA DATA ANALYSIS------------------------------

# Get a vector of all score values (score_0, score_1, score_2, score_3)
allScores = noFOLong[(which(noFOLong$variable=="score_0" | noFOLong$variable=="score_1" |
                              noFOLong$variable=="score_2" | noFOLong$variable=="score_3")), 7]

allScoresRows = which(noFOLong$variable=="score_0" | noFOLong$variable=="score_1" |
                        noFOLong$variable=="score_2" | noFOLong$variable=="score_3")

# Get a vector of all time values (time_0, time_1, time_2, time_3)
allTimes = noFOLong[(which(noFOLong$variable=="score_0" | noFOLong$variable=="score_1" |
                             noFOLong$variable=="score_2" | noFOLong$variable=="score_3")), 7]

allTimesRows = which(noFOLong$variable=="time_0" | noFOLong$variable=="time_1" |
                       noFOLong$variable=="time_2" | noFOLong$variable=="time_3")

#------------------------------ADDITIONAL BOX PLOTS------------------------------

# Make a boxplot of scores across ICV
boxplot(allScores ~ noFOLong[allScoresRows,2], data = noFOLong, xlab = "ICV",
        ylab = "All scores", main = "Boxplot - ICV(All scores)") + 
  abline(v=c(12.5), col=c("blue"), lty=c(2), lwd=c(2))

# Make a boxplot of scores across IQ
boxplot(allScores ~ noFOLong[allScoresRows,4], data = noFOLong, xlab = "IQ",
        ylab = "All scores", main = "Boxplot - IQ(All scores)") +
  abline(v=c(24.5), col=c("blue"), lty=c(2), lwd=c(1))

# Make a boxplot of times across IQ
boxplot(allTimes ~ noFOLong[allTimesRows,4], data = noFOLong, xlab = "IQ",
        ylab = "All times", main = "Boxplot - IQ(All times)") +
  abline(v=c(24.5), col=c("blue"), lty=c(2), lwd=c(1))

# Make a boxplot of times across ICV
boxplot(allTimes ~ noFOLong[allTimesRows,2], data = noFOLong, xlab = "ICV",
        ylab = "Al times", main = "Boxplot - ICV(All times)") + 
  abline(v=c(12.5), col=c("blue"), lty=c(2), lwd=c(2))

# Make a boxplot of scores across ICV
boxplotScoresICV = ggplot(noFOLongScores, aes(ICV, value, fill=variable)) + 
  geom_boxplot() + labs(x="ICV", y="Score") +
  theme(legend.position="bottom") +
  geom_vline(xintercept=100.5, linetype='dashed', color='blue', size=0.5)

boxplotScoresICV

# Make a boxplot of scores across IQ
boxplotScoresIQ = ggplot(noFOLongScores, aes(IQ, value, fill=variable)) + 
  geom_boxplot() + labs(x="IQ", y="Score") +
  theme(legend.position="bottom") +
  geom_vline(xintercept=107.5, linetype='dashed', color='blue', size=0.5)

boxplotScoresIQ

# Make a boxplot of times across ICV
boxplotTimesICV = ggplot(noFOLongTimes, aes(ICV, value, fill=variable)) + 
  geom_boxplot() + labs(x="ICV", y="Time") +
  theme(legend.position="bottom") +
  geom_vline(xintercept=100.5, linetype='dashed', color='blue', size=0.5)

boxplotTimesICV

# Make a boxplot of times across IQ
boxplotTimesIQ = ggplot(noFOLongTimes, aes(IQ, value, fill=variable)) + 
  geom_boxplot() + labs(x="IQ", y="Time") +
  theme(legend.position="bottom") +
  geom_vline(xintercept=107.5, linetype='dashed', color='blue', size=0.5)

boxplotTimesIQ

#------------------------------ADDITIONAL SCATTERPLOTS------------------------------

# Make a scatterplot of scores across ICV
scatterplotScoresICV = ggplot(noFOLongScores,aes(ICV, value, colour=variable)) +
  ggtitle("Scatterplot - ICV(All scores)") +
  geom_point(shape=19, size=2) + labs(x="ICV", y="Score") +
  geom_vline(xintercept=100.5, linetype='dashed', color='blue', size=0.5)

scatterplotScoresICV  

# Make a scatterplot of times across ICV
scatterplotTimesICV = ggplot(noFOLongTimes,aes(ICV, value, colour=variable)) +
  ggtitle("Scatterplot - ICV(All times)") +
  geom_point(shape=19, size=2) + labs(x="ICV", y="Time") +
  geom_vline(xintercept=100.5, linetype='dashed', color='blue', size=0.5)

scatterplotTimesICV

#------------------------------ADDITIONAL REGRESSION MODELS------------------------------

# Create third regression model - score, time (DVs) and IQ (IV)

regressionScoresTimesIQ = lm(value ~ IQ, data=noFOLongScoresAndTimes, na.action=na.exclude)

# Beta=0.04, t=0.68, p=0.50
# F(1,598)=0.46, p=0.50, adj R2=-0.0009
# VERY HIGH unsystematic variance, almost no correlation and even 
# this bare R2 pointing to any directionality is not significant anyway.
summary(regressionScoresTimesIQ)

# Create fourth regression model just to see if extra predictor improves it
# (expected the contrary as no correlation with ICV was found) - score (DV) and ICV, IQ (IVs)

regressionScoresICVIQ = lm(value ~ ICV + IQ, data=noFOLongScores, na.action=na.exclude)

# ICV
# Beta=-0.1, t=-2.02, p=0.04
# IQ
# Beta=0.18, t=7.69, p=2.1e-13

# F(2,297)=29.59, p=1.9e-12, adj R2=0.1606
# Higher unsystematic variance, but the R2 is the best one achieved so far
# and this is the most significant result so far for ICV.
summary(regressionScoresICVIQ)

# Create fifth regression model just to see if model is better for time as well
# time (DV) and ICV, IQ (IVs)

regressionTimesICVIQ = lm(value ~ ICV + IQ, data=noFOLongTimes, na.action=na.exclude)

# ICV
# Beta=0.03, t=0.95, p=0.34
# IQ
# Beta=-0.08, t=-4.95, p=1.2e-06

# F(2,297)=12.34, p=1.7e-05, adj R2=0.07053
# Even higher unsystematic variance, a fraction in improvement of R2 over only IQ as a predictor
# (0.07053 > 0.0649) and this one is less significant than model 4.
summary(regressionTimesICVIQ)

#------------------------------RED FLAGS IN ASSUMPTIONS - ABNORMAL DISTRIBUTION OF time_3------------------------------

# time_3

# Significantly abnormal data for time_3 in lowerICV quantile
# lower ICV SW=0.94, p=0.026
by(noFO$time_3, noFO$QuantileICV, stat.desc, norm=T)
# Still slightly abnormal dataset: lowerICV, time_3, SW=0.91, p=0.03, removing the outlier
# improved the normality a bit

# noFO$time_3 = ifelse(noFO$time_3==54, NA, noFO$time_3)

# Removing the outlier changes SW's p from 0.02 to 0.03, but causes a major issue of
# other time values missing for ANOVA and standard residuals for regression model 2.
# Deciding to leave it in because this 1 outlier takes with itself another 3 data points.
# Significance increase definitely not worth it.

# Out of 16 datasets tested with SW only these two have shown deviation from normality:
# 4 scores in lowerICV - 2 of which abnormal, but not greatly significant
# 4 times in lowerICV
# 4 scores in lowerIQ
# 4 scores in upperIQ

### Conclusion: Individual score and time groups are distributed normally in both quantiles of ICV and IQ.

#------------------------------RED FLAGS IN ASSUMPTIONS - ABNORMAL DISTRIBUTION OF 3xRTI and 1xRSI------------------------------

# lower ICV W=94, p=0.013
by(noFO$RTI2, noFO$QuantileICV, stat.desc, norm=T)
# lower ICV SW=0.94, p=0.022
by(noFO$RTI3, noFO$QuantileICV, stat.desc, norm=T)
# Conclusion: Decided not to remove any outliers - will use ANOVA which is quite robust against them

# lower IQ W=0.93, p=0.0087
by(noFO$RTI2, noFO$QuantileIQ, stat.desc, norm=T)
# lower IQ W=0.95, p=0.04
by(noFO$RSI2, noFO$QuantileIQ, stat.desc, norm=T)
# Conclusion: No outliers removed, same case as with anova, this is not a case of t-test anymore, multi-level analysis
# which goes along the accepted Ho (stating that means of score and times are different in between
# lower and upper IQ) will be quite robust as well

# On top of that, this is a value that was calculated in a custom fashion to represent relative change/imrpvement
# in score or time which were calculated from data that was proven to be normally distributed already,
# as proven in the section above - only  time_0 and time_3 had outliers.

#------------------------------RED FLAGS IN ASSUMPTIONS - VIOLATED INDEPENDENCY IN REGRESSION------------------------------

# Violated independence in regression model 1
# Standardized residuals versus ID (independence)

# DW=1.67, p=0.0044
dwtest(regressionScoresIQ, alternative="two.sided")

model1Indep = ggplot(noFOLongScores, aes(ID, model1StandResid)) +
  geom_point() + labs(x="Subject ID", y="Standardized residuals") +
  geom_smooth(method="lm", colour="Blue")

# Violated independence in regression model 2
# Standardized residuals versus ID (independence)

# DW=1.38, p=6.6e-08
dwtest(regressionTimesIQ, alternative="two.sided")

model2Indep = ggplot(noFOLongTimes, aes(ID, model2StandResid)) +
  geom_point() + labs(x="Subject ID", y="Standardized residuals") +
  geom_smooth(method="lm", colour="Blue")

# There is 75 subjects in placebo (noFO, no Fish Oil) group.
# Each subject (each unique ID) has 4 score values and 4 time values assigned to them.
psych::describe(noFO)

# This changes when data is melted into a long format and due to the nature of the experiment, now
# in long data each ID is repeated 4 times for time values
# e.g. ID127554time_0, ID127554time_1, ID127554time_2, ID127554time_3
#
# Same case for scores
# e.g. ID127554score_0, ID127554score_1, ID127554score_2, ID127554score_3
# Which quadruples the data entries for the same ID (and this long format was used for the regression model)
length(noFOLongTimes$ID)
length(noFOLongScores$ID)

### Conclusion: Violation of independency is absolutely expected as in long format data
# we have 4 measurements per each ID, which is the case because of the nature of the experiment;
# taking measurements for baseline (value_0) and across 3 weeks (value_1, value_2, value_3)
# Proceeding with regression analysis makes sense in that case

#------------------------------RED FLAGS IN ASSUMPTIONS - ABNORMAL DISTRIBUTION OF RESIDUALS------------------------------

# Model 1

# More than 1% of the sample has standardized values
# with absolute values > 2.58; there is evidence
# that the level of error may be unacceptable

# Way more than 5% of the sample has standardized values
# with absolute values > 1.96, there is strong evidence that
# the model is a poor representation of the actual data
stResMod1V = as.vector(noFOLongScores$model1StandResid)
nGreaterThanAbs3.3 = length(c(stResMod1V[stResMod1V< -3.3], stResMod1V[stResMod1V>3.3]))	# > |3.3|
nGreaterThanAbs2.58 = length(c(stResMod1V[stResMod1V< -2.58], stResMod1V[stResMod1V>2.58]))	# > |2.58|
nGreaterThanAbs1.96 = length(c(stResMod1V[stResMod1V< -1.96], stResMod1V[stResMod1V>1.96]))	# > |1.96|

# 0% > |3.3|
(nGreaterThanAbs3.3/sampleSize) * 100
# 2.67% > |2.58|
(nGreaterThanAbs2.58/sampleSize) * 100
# 14.67% > |1.96|
(nGreaterThanAbs1.96/sampleSize) * 100

# Model 2

# More than 1% of the sample has standardized values
# with absolute values > 2.58; there is evidence
# that the level of error may be unacceptable

# Way more than 5% of the sample has standardized values
# with absolute values > 1.96, there is strong evidence that
# the model is a poor representation of the actual data
stResMod1V = as.vector(noFOLongTimes$model2StandResid)
nGreaterThanAbs3.3 = length(c(stResMod1V[stResMod1V< -3.3], stResMod1V[stResMod1V>3.3]))	# > |3.3|
nGreaterThanAbs2.58 = length(c(stResMod1V[stResMod1V< -2.58], stResMod1V[stResMod1V>2.58]))	# > |2.58|
nGreaterThanAbs1.96 = length(c(stResMod1V[stResMod1V< -1.96], stResMod1V[stResMod1V>1.96]))	# > |1.96|

# 0% > |3.3|
(nGreaterThanAbs3.3/sampleSize) * 100
# 2.67% > |2.58|
(nGreaterThanAbs2.58/sampleSize) * 100
# 14.67% > |1.96|
(nGreaterThanAbs1.96/sampleSize) * 100

# Conclusion Model 1 and 2 (same % of residuals outside z-score ranges):
# Not significantly above 1% of sample with standardized values above > 2.58;
# Needs caution with drawing conclusions.
# However, the error does not seem to be significant
# It is certain however that the model is a poor representation of 
# the actual population, increment of the sample size is strongly recommended

# Note however, that this again comes from the analysis on the residuals
# which were proven to be not independent due to the nature of the experimental data
# converted into a long format (8 measurements for each ID)
# which suggests that this might have been a cause for abnormal
# distribution of the residuals as well.

#------------------------------RED FLAGS IN ASSUMPTIONS - DEPARTURE FROM SPHERICITY------------------------------

# Time means differences across ICV - violated sphericity

# Initial:
# F(3,222)=130.04, p<2.2e-16
summary(repmANOVATimesICV)

Df1 = 2*0.88408
Df2 = 148*0.88408
Df1
Df2

# Conclusion: correct with GG epsilon.
# Greenhouse-Geisser (GG eps) corrected:
# F(1.77, 130.84)=130.04, p<2.2e-16

# Time means differences across IQ - violated sphericity

# Initial:
# F(3,219)=0.07, p=0.98
summary(repmANOVATimesIQ)

Df1 = 3*0.88404
Df2 = 219*0.88404
Df1
Df2

# Conclusion: correct with GG epsilon.
# Greenhouse-Geisser (GG eps) corrected:
# F(2.65, 193.60)=130.04, p=0.97