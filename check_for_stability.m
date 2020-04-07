clear;
close all;  % this one has 3 text files of the standard options

A1 = readtable('Normal1_Club.txt');  %reads in text file
A2 = readtable('Normal2_Club.txt');  %reads in text file
A3 = readtable('Normal3_Club.txt');  %reads in text file
M1 = (A1{:,1}+A2{:,1}+A3{:,1})/3; %mean

A4 = readtable('Normal1_totalDrinksConsumed.txt');  %reads in text file
A5 = readtable('Normal2_totalDrinksConsumed.txt');  %reads in text file
A6 = readtable('Normal3_totalDrinksConsumed.txt');  %reads in text file
M2 = (A4{:,1}+A5{:,1}+A6{:,1})/3; %mean

B = importdata('Normal1_Club.txt');
steps = size(A1(:,1));        %checks how many steps where taken in code
t = 0:(10/(steps(1) -1)):10;  %last number here represent total time open in hours, has to be same as in simulation!
%%
    figure(1)
    plot(t,A1{:,1})
    hold on;
    plot(t,A2{:,1})
    hold on;
    plot(t,A3{:,1})
    hold on;
    plot(t,M1)
    hold on;
    legend('1','2','3','mean')
    grid on;
    xlabel('Time [Hour]');
    ylabel('Total money spent [Euro]');
    
    figure(2)
    plot(t,A1{:,2})
    hold on;
    plot(t,A2{:,2})
    hold on;
    plot(t,A3{:,2})
    legend('1','2','3')
    grid on;
    xlabel('Time [Hour]');
    ylabel('Amount of people');
    
    figure(3)
    plot(t,A1{:,3})
    hold on;
    plot(t,A2{:,3})
    hold on;
    plot(t,A3{:,3})
    legend('1','2','3')
    grid on;
    xlabel('Time [Hour]');
    ylabel('Amount of people dancing');
    
    figure(4)
    plot(t,A4{:,1})
    hold on;
    plot(t,A5{:,1})
    hold on;
    plot(t,A6{:,1})
    hold on;
    plot(t,M2)
    hold on;
    legend('1','2','3','mean')
    grid on;
    xlabel('Time [Hour]');
    ylabel('Total amount of consumed drinks');
