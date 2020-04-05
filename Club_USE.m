clear;
close all;

A = readtable('Club.txt');   %reads in text file
B = importdata('Club.txt');
steps = size(A(:,1));        %checks how many steps where taken in code
t = 0:(10/(steps(1) -1)):10; %last number here represent total time open in hours, has to be same as in simulation!
%%
    figure(1)
    plot(t,A{:,1})
    grid on;
    xlabel('Time [Hour]');
    ylabel('Total money spent');
    figure(2)
    plot(t,A{:,2})
    grid on;
    xlabel('Time [Hour]');
    ylabel('Amount of people');
    figure(3)
    plot(t,A{:,3})
    grid on;
    xlabel('Time [Hour]');
    ylabel('Amount of people dancing');

