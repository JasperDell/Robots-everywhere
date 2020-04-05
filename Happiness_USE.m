clear;
close all;

A = readtable('Happiness.txt'); %reads in text file
B = importdata('Happiness.txt');
K = size(B.textdata);           %used to check how many people in code
steps = size(A(:,1));           %checks how many steps where taken in code
t = 0:(10/(steps(1) -1)):10;    %last number here represent total time open in hours, has to be same as in simulation!
%%
for i = 1:(K(2))
    figure(i)
    plot(t,A{:,i})
    grid on;
    xlabel('Time [Hour]');
    ylabel('Happiness');
end
